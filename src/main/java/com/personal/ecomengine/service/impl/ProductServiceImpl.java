package com.personal.ecomengine.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.personal.ecomengine.dto.FakeProductServiceDto;
import com.personal.ecomengine.model.Category;
import com.personal.ecomengine.model.Product;
import com.personal.ecomengine.model.Rating;
import com.personal.ecomengine.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
    private RestTemplate restTemplate;
    private WebClient webClient;

    @Autowired
    public ProductServiceImpl(RestTemplate restTemplate, WebClient webClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }
    @Override
    public Product getProductById(final long id) {
        //Third party API call to get product by id
        Mono<FakeProductServiceDto> productServiceDto = webClient.get()  // Create a GET request
            .uri("/products/"+id)  // URI used with the base URL
            .retrieve()  // Retrieve the response
            .bodyToMono(FakeProductServiceDto.class);
        //Actual E-Comm will have DB call to get product by ID
        if(productServiceDto!=null){
            return convertProductDto(productServiceDto.block());
        }
        return null;
    }
    @Override
    public List<Product> getProducts() {
        //this.restTemplate.getForObject("https://fakestoreapi.com/products", ArrayList<FakeProductServiceDto>.class);
        Flux<FakeProductServiceDto> productServiceDto = webClient.get()  // Create a GET request
            .uri("/products/")  // URI used with the base URL
            .retrieve()  // Retrieve the response
            .bodyToFlux(FakeProductServiceDto.class);
        //Actual E-Comm will have DB call to get product by ID
        ArrayList<Product> products = new ArrayList<>();
        //productServiceDto.subscribe(product -> products.add(convertProductDto(product)));
        Mono<List<FakeProductServiceDto>> productServiceDtoList = productServiceDto.collectList();
        for(FakeProductServiceDto product:productServiceDtoList.block()){
            products.add(convertProductDto(product));
        }
        return products;

    }
    private Product convertProductDto(final FakeProductServiceDto productServiceDto) {
        Category category = new Category();
        category.setName(productServiceDto.getCategory());
        Rating rating = new Rating();
        rating.setRate(productServiceDto.getRating().getRate());
        rating.setCount(productServiceDto.getRating().getCount());
        Product product = new Product();
        product.setId(productServiceDto.getId());
        product.setDescription(productServiceDto.getDescription());
        product.setPrice(productServiceDto.getPrice());
        product.setTitle(productServiceDto.getTitle());
        product.setImageUrl(productServiceDto.getImage());
        product.setRating(rating);
        product.setCategory(category);
        return product;
    }
}
