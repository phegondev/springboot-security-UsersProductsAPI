package com.den.MySpringSecurityTuts.controller;

import com.den.MySpringSecurityTuts.model.Product;
import com.den.MySpringSecurityTuts.repository.ProductRepo;
import com.den.MySpringSecurityTuts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String goHome(){
        return "HEY!!, You are at Home. FEEL FREE 😁";
    }
    @GetMapping("/product/all")
    public List<Product> findAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/product/{name}")
    public Optional<Product> findProductByName(@PathVariable String name){
        return productService.findProductByName(name);
    }
    @PostMapping("/product/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

}
