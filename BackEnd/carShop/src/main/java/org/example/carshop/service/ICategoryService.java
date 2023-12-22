package org.example.carshop.service;


import org.example.carshop.entity.Category;

import java.util.List;
public interface ICategoryService {
    List<Category> getAllCategory();

    Category findByIdCategory(Long id);
}
