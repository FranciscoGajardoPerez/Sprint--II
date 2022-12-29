package com.example.be_java_hisp_w19_g2.repositories;

import com.example.be_java_hisp_w19_g2.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    UserRepository userRepository;

    public List<Product> products = new ArrayList<>();

    public ProductRepository() {
        this.products = productsList();
    }

    public List<Product> productsList() {
        products.add(new Product("Skate", "Sport",    "Electro", "Apple",     "Grey"));
        products.add(new Product("Longboard", "Sport",    "Electro", "Apple",     "Grey"));
        products.add(new Product("Smart TV", "Entretei",    "Electro", "Apple",     "Grey"));
        products.add(new Product("Lambo", "Car",    "Electro", "Apple",     "Grey"));
        products.add(new Product("Guitar", "Music",    "Electro", "Apple",     "Grey"));
        return products;
    }

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> getProducts() {
        return products;
    }
}
