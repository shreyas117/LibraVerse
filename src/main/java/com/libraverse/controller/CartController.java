package com.libraverse.controller;

import com.libraverse.model.Cart;
import com.libraverse.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
    @Autowired
    private CartRepository cartRepository;


    @PostMapping("/addToCart")
    private void add(@RequestBody Cart cart){
        cartRepository.save(cart);
    }

    @GetMapping("/fetchCart")
    public List<Cart> fetchCart(){
        return cartRepository.findAll();
    }


}
