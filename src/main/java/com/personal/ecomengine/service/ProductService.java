package com.personal.ecomengine.service;

import java.util.List;

import com.personal.ecomengine.model.Product;

public interface ProductService {
    Product getProductById(long id);
    List<Product> getProducts();
}
