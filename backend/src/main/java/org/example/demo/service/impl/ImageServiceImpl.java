package org.example.demo.service.impl;

import org.example.demo.entity.Image;
import org.example.demo.exception.NotFoundException;
import org.example.demo.repository.ImageRepository;
import org.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getListImage() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImageById(long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image not found width id :" + id));
        return image;
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public List<Image> getListByUser(long userId) {
        List<Image> images = imageRepository.getListImageOfUser(userId);
        return images;
    }

    @Override
    public void deleteImage(long id) {

    }
}
