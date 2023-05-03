package com.den.MySpringSecurityTuts.repository;

import com.den.MySpringSecurityTuts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Integer>{
    @Query(value = "select * from products where name = ?1", nativeQuery = true)
    public Optional<Product> findProductByName(String name);
}
