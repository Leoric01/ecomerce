package com.leoric.eshop.services;

import com.leoric.eshop.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getProducts();
}
