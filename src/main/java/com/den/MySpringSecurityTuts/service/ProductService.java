package com.den.MySpringSecurityTuts.service;

import com.den.MySpringSecurityTuts.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    Optional<Product> findProductByName(String name);
    String saveProduct(Product product);
}
