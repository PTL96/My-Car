package org.example.carshop.service;

import org.example.carshop.dto.History;
import org.example.carshop.dto.ProductView;
import org.example.carshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<ProductView> getAllProduct(String name, String category, Pageable pageable);

    Product findByIdProduct(Long id);

    void save(Product product);

    void deleteProduct(Long id);

    Page<History> getAllHistory(String name, String category, Pageable pageable);
}
