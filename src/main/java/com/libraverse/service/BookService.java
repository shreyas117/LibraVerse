package com.libraverse.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraverse.model.Book;
import com.libraverse.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Value("${google.books.api.key}")
    private String apiKey;

    private static final String GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes?q=subject:{subject}&key={apiKey}";

    public void fetchAndSaveBooks(String subject) {
        RestTemplate restTemplate = new RestTemplate();
        String url = GOOGLE_BOOKS_API_URL.replace("{subject}", subject).replace("{apiKey}", apiKey);
        String jsonResponse = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode itemsNode = rootNode.path("items");
            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    JsonNode volumeInfoNode = itemNode.path("volumeInfo");
                    JsonNode saleInfoNode = itemNode.path("saleInfo");

                    if (volumeInfoNode.isMissingNode() || volumeInfoNode.path("title").isMissingNode()) {
                        continue; // Skip this item if title is missing
                    }

                    Book book = new Book();
                    book.setTitle(volumeInfoNode.path("title").asText());

                    JsonNode isbnNode = volumeInfoNode.path("industryIdentifiers").isArray() && volumeInfoNode.path("industryIdentifiers").size() > 0
                            ? volumeInfoNode.path("industryIdentifiers").get(0).path("identifier")
                            : null;
                    book.setIsbn(isbnNode != null ? isbnNode.asText("N/A") : "N/A");

                    book.setDescription(volumeInfoNode.path("description").asText());
                    book.setImageUrl(volumeInfoNode.path("imageLinks").path("thumbnail").asText());
                    book.setPrice(saleInfoNode.path("listPrice").path("amount").asDouble(0.0) != 0.0
                            ? new BigDecimal(saleInfoNode.path("listPrice").path("amount").asDouble(0.0))
                            : new BigDecimal("0.00"));
                    book.setStock(10); // Default stock
                    book.setRating(volumeInfoNode.path("averageRating").asDouble());

                    bookRepository.save(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
