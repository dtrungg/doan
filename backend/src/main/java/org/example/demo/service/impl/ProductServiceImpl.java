package org.example.demo.service.impl;

import org.example.demo.entity.Category;
import org.example.demo.entity.Image;
import org.example.demo.entity.Product;
import org.example.demo.exception.NotFoundException;
import org.example.demo.model.request.CreateProductRequest;
import org.example.demo.repository.CategoryRepository;
import org.example.demo.repository.ImageRepository;
import org.example.demo.repository.ProductRepository;
import org.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Product> getList() {
        return productRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Product getProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        return product;
    }


    @Override
    public Product createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        product.setCategory(category);

        Set<Image> images = new HashSet<>();
        for (long imageId : request.getImageIds()) {
            Image image = imageRepository.findById(imageId)
                    .orElseThrow(() -> new NotFoundException("Not Found Image With Id: " + imageId));
            images.add(image);
        }
        product.setImages(images);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(long id, CreateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        product.setCategory(category);

        Set<Image> images = new HashSet<>();
        for (long imageId : request.getImageIds()) {
            Image image = imageRepository.findById(imageId)
                    .orElseThrow(() -> new NotFoundException("Not Found Image With Id: " + imageId));
            images.add(image);
        }
        product.setImages(images);
        productRepository.save(product);

        return product;
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        product.getImages().remove(this);
        productRepository.delete(product);
    }

    @Override
    public List<Product> getListNewst(int number) {
        List<Product> list = productRepository.getListNewest(number);
        return list;
    }

    @Override
    public List<Product> getListByPrice() {
        return productRepository.getListByPrice();
    }

    @Override
    public List<Product> findRelatedProduct(long id) {
        List<Product> list = productRepository.findRelatedProduct(id);
        return list;

    }

    @Override
    public List<Product> getListProductByCategory(long id) {
        List<Product> list = productRepository.getListProductByCategory(id);
        return list;
    }

    @Override
    public List<Product> getListByPriceRange(long id, int min, int max) {
        List<Product> list = productRepository.getListProductByPriceRange(id, min, max);
        return list;
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        List<Product> list = productRepository.searchProduct(keyword);
        return list;
    }


}
