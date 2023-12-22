package org.example.carshop.service.impl;

import org.example.carshop.entity.Category;
import org.example.carshop.repository.ICategoryRepository;
import org.example.carshop.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepository iCategoryRepository;
    public List<Category> getAllCategory() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Category findByIdCategory(Long id) {
        return iCategoryRepository.findById(id).get();
    }
}
