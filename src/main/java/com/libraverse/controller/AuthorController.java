package com.libraverse.controller;

import com.libraverse.model.Author;
import com.libraverse.model.Book;
import com.libraverse.repository.AuthorRepository;
import com.libraverse.repository.BookRepository;
import com.libraverse.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/getAuthors")
public class AuthorController {

    @Autowired
    //private AuthorService authorService
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllAuthors(){
        return bookRepository.findAll();
        //return authorService.getAllAuthors();
    }
}
