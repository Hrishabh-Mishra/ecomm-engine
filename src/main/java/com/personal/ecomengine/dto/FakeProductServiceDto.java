package com.personal.ecomengine.dto;

import com.personal.ecomengine.model.Rating;

import lombok.Data;

@Data
public class FakeProductServiceDto {
    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
}
