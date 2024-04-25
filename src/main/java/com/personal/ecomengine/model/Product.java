package com.personal.ecomengine.model;

import lombok.Data;

@Data
public class Product {
    private long id;
    private String title;
    private double price;
    private String description;
    private Category category;
    private String imageUrl;
    private Rating rating;
}
