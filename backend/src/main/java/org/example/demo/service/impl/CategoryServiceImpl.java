package org.example.demo.service.impl;

import org.example.demo.entity.Category;
import org.example.demo.exception.NotFoundException;
import org.example.demo.model.request.CreateCategoryRequest;
import org.example.demo.repository.CategoryRepository;
import org.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        List<Category> list = categoryRepository.findAll(Sort.by("id").descending());
        return list;
    }

    @Override
    public Category createCategory(CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setEnable(false);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category updateCategory(long id, CreateCategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + id));
        category.setName(request.getName());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void enableCategory(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + id));
        if (category.isEnable()) {
            category.setEnable(false);
        } else {
            category.setEnable(true);
        }
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + id));
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getListEnabled() {
        List<Category> list = categoryRepository.findALLByEnabled();
        return list;
    }

}
