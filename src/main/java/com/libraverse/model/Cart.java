package com.libraverse.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class Cart {
    @Id
    private Long id;

   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @OneToOne
   // @MapsId
    //@JoinColumn(name = "id")
    //private Book book;
    //private int id;
    private int quantity;
    private String title;
    private String imageUrl;
    private double price;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
