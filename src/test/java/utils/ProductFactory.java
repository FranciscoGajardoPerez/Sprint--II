package utils;

import com.example.be_java_hisp_w19_g2.dtos.ProductDTO;
import com.example.be_java_hisp_w19_g2.entities.Product;

public class ProductFactory {
    public static Product createProduct(){
        return Product.builder()
                        .productName("Silla Gamer")
                        .productId(1)
                        .type("Mueble")
                        .color("Gris")
                        .brand("Corsair")
                        .notes("N/A").build();
    }

    public static Product createProductWithOutId(){
        return Product.builder()
                        .productName("Silla Gamer")
                        .type("Mueble")
                        .color("Gris")
                        .brand("Corsair")
                        .notes("N/A").build();
    }

    public static ProductDTO createProductDTO(){
        return ProductDTO.builder()
                        .productName("Silla Gamer")
                        .type("Mueble")
                        .color("Gris")
                        .brand("Corsair")
                        .notes("N/A").build();
    }
}
