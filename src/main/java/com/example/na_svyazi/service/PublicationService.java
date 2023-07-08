package com.example.na_svyazi.service;


import com.example.na_svyazi.entity.Publication;
import com.example.na_svyazi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicationService{

    Publication getPublicationById(Long id);

    void savePublication(User profile, Publication publication, MultipartFile file);

    void deletePublication(Long id);

    List<Publication> subscriberPublications(User profile);
}
