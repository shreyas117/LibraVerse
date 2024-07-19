package com.libraverse.controller;

import com.libraverse.model.Cart;
import com.libraverse.repository.BookRepository;
import com.libraverse.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;


    @PostMapping("/addToCart")
    private void add(@RequestBody Cart cart){

        if(!cartRepository.existsById(cart.getId())){
            cartRepository.save(cart);
        }
       else {
            int count = cart.getQuantity();
            System.out.print(count);
            int old = cartRepository.findQuantityById(cart.getId());
            int res = old + count;
            cartRepository.updateQuantityById(cart.getId(), res);
            //cartRepository.save(cart);
        }
    }
    @GetMapping("/fetchCart")
    public List<Cart> fetchCart(){
        return cartRepository.findAll();
    }

    @PostMapping("/deleteCart")

    public void deleteCart(){
         cartRepository.deleteAll();
    }

    @PostMapping("/deleteCartItem/{id}")
    public void getUserById(@PathVariable("id") Long id) {
        cartRepository.deleteById(id);
    }



}
