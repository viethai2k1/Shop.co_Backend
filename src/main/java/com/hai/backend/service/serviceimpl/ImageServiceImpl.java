package com.hai.backend.service.serviceimpl;

import com.hai.backend.entity.ImageEntity;
import com.hai.backend.repository.ImageRepository;
import com.hai.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Override
    public ImageEntity saveImage(ImageEntity image) {
        return imageRepository.save(image);
    }

    @Override
    public Boolean check(int id) {
        return imageRepository.existsById(id);
    }
}
