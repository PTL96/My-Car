package org.example.carshop.service.impl;

import org.example.carshop.dto.History;
import org.example.carshop.dto.ProductView;
import org.example.carshop.entity.Product;
import org.example.carshop.repository.IProductRepository;
import org.example.carshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository iProductRepository;
    public Page<ProductView> getAllProduct(String name, String category, Pageable pageable) {
        return iProductRepository.findAllPage(name, category, pageable);
    }

    @Override
    public Product findByIdProduct(Long id) {
        return iProductRepository.findById(id).get();
    }

    @Override
    public void save(Product product) {
        product.setFlagDelete(true);
        iProductRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = this.findByIdProduct(id);
        product.setFlagDelete(false);
        iProductRepository.save(product);
    }

    @Override
    public Page<History> getAllHistory(String name, String category, Pageable pageable) {
        return iProductRepository.historyPage(name, category,pageable);
    }
}
