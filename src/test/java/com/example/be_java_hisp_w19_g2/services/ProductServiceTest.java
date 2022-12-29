package com.example.be_java_hisp_w19_g2.services;

import com.example.be_java_hisp_w19_g2.dtos.PostDTO;
import com.example.be_java_hisp_w19_g2.dtos.ProductDTO;
import com.example.be_java_hisp_w19_g2.entities.Product;
import com.example.be_java_hisp_w19_g2.repositories.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.ProductFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    @DisplayName("Verifica que se puede postear correctamente un producto")
    public void postProductTest() {
        // Arrange
        ProductDTO productDTO = ProductFactory.createProductDTO();
        Product product = ProductFactory.createProductWithOutId();
        PostDTO postDTO = new PostDTO();
        postDTO.setProduct(productDTO);
        Mockito.when(productRepository.addProduct(product)).thenReturn(product);
        // Act
        productService.postProduct(postDTO);
        // Assert
        Mockito.verify(productRepository, Mockito.atLeastOnce()).addProduct(product);

    }
}