package com.libraverse.controller;

import com.libraverse.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/fetch")
    public ResponseEntity<String> fetchBooks(@RequestParam String subject) {
        bookService.fetchAndSaveBooks(subject);
        return ResponseEntity.ok("Books fetched and saved successfully");
    }

    // Other existing methods for CRUD operations
}
