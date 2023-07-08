package com.example.na_svyazi.utils;

import com.example.na_svyazi.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ImageUtil {
    public static Image fileToImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        return image;
    }
}
