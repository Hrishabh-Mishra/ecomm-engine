package com.personal.ecomengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.ecomengine.dto.ResponseDto;
import com.personal.ecomengine.model.Product;
import com.personal.ecomengine.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public ResponseDto<Product> getProduct(@PathVariable final long id) {
        ResponseDto<Product> response = new ResponseDto<>(productService.getProductById(id));
        return response;
    }
    @GetMapping("/get")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
