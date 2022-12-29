package com.example.be_java_hisp_w19_g2.services;

import com.example.be_java_hisp_w19_g2.dtos.PostDTO;
import com.example.be_java_hisp_w19_g2.dtos.ProductDTO;

public interface IProductService {
    public ProductDTO postProduct(PostDTO postDTO);
}
