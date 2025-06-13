package com.seb.microservices.product.service;

import com.seb.microservices.product.dto.ProductRequest;
import com.seb.microservices.product.dto.ProductResponse;
import com.seb.microservices.product.model.Product;
import com.seb.microservices.product.repository.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final IProductRepository _productRepository;

    public ProductService(IProductRepository productRepository) {
        this._productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        _productRepository.save(product);
        log.info("Product is saved");

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public List<ProductResponse> getAllProducts() {
        log.info("Getting all products");
        return _productRepository.findAll(
                ).stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                )).toList();
    }

}
