package org.example.demo.service;

import org.example.demo.entity.Category;
import org.example.demo.model.request.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    List<Category> getListEnabled();

    Category createCategory(CreateCategoryRequest request);

    Category updateCategory(long id,CreateCategoryRequest request);

    void enableCategory(long id);

    void deleteCategory(long id);
}
