package com.example.be_java_hisp_w19_g2.services;

import com.example.be_java_hisp_w19_g2.dtos.PostDTO;
import com.example.be_java_hisp_w19_g2.dtos.ProductDTO;
import com.example.be_java_hisp_w19_g2.entities.Product;
import com.example.be_java_hisp_w19_g2.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDTO postProduct(PostDTO postDTO) {
        Product product = new Product(
            postDTO.getProduct().getProductName(),
            postDTO.getProduct().getType(),
            postDTO.getProduct().getBrand(),
            postDTO.getProduct().getColor(),
            postDTO.getProduct().getNotes()
        );
        productRepository.addProduct(product);
        return postDTO.getProduct();
    }
}
