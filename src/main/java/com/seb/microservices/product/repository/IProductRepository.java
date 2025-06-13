package com.seb.microservices.product.repository;

import com.seb.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductRepository extends MongoRepository<Product, String> {
}
