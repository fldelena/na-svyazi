package com.example.na_svyazi.service.impl;

import com.example.na_svyazi.dao.ImageRepository;
import com.example.na_svyazi.entity.Image;
import com.example.na_svyazi.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
