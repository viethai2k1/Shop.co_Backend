package com.hai.backend.service;

import com.hai.backend.entity.ImageEntity;
import org.springframework.stereotype.Service;

@Service
public interface ImageService {
    ImageEntity saveImage(ImageEntity image);

    Boolean check(int id);
}
