package com.libraverse.controller;

import com.libraverse.model.Book;
import com.libraverse.repository.BookRepository;
import com.libraverse.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/fetch")
    public ResponseEntity<String> fetchBooks(@RequestParam String subject) {
        bookService.fetchAndSaveBooks(subject);
        return ResponseEntity.ok("Books fetched and saved successfully");
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();

        //return authorService.getAllAuthors();
    }



    @GetMapping("/search/{title}")
    public List<Book> searchResult(@PathVariable String title){
        //System.out.println("title"+title);

        return bookRepository.findByName(title);
       // bookRepository.find
    }
    @GetMapping("/search/")
    public List<Book> emptySearch(){
        //System.out.println("title"+title);

        return bookRepository.findAll();
        // bookRepository.find
    }


}
