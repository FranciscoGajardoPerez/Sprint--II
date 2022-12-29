package com.example.be_java_hisp_w19_g2.repositories;

import com.example.be_java_hisp_w19_g2.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
    ProductRepository productRepository = new ProductRepository();

    @Test
    @DisplayName("Verifica que se agrega correctamente un producto nuevo.")
    public void addProductTest() {
        // Arange
        Product product = new Product("PC", "Computer", "Aorus", "White", "Black");
        Integer expectedSize = productRepository.getProducts().size() + 1;
        // Act
        productRepository.addProduct(product);
        // Assert
        Assertions.assertEquals(expectedSize, productRepository.getProducts().size());
    }
}
