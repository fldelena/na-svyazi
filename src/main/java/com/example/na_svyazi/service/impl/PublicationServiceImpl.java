package com.example.na_svyazi.service.impl;

import com.example.na_svyazi.dao.PublicationRepository;
import com.example.na_svyazi.entity.Image;
import com.example.na_svyazi.entity.Publication;
import com.example.na_svyazi.entity.User;
import com.example.na_svyazi.service.PublicationService;
import com.example.na_svyazi.service.UserService;
import com.example.na_svyazi.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    private final UserService userService;

    @Override
    public Publication getPublicationById(Long id) {
        return publicationRepository.findById(id).orElseThrow();
    }

    //обработать ошибку если изображение не удалось получить
    @Override
    public void savePublication(User profile, Publication publication, MultipartFile file) {
        publication.setPublicationToUser(profile);
        Image image;
        if (file != null) {
            try {
                image = ImageUtil.fileToImage(file);
                publication.getImages().add(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        publicationRepository.save(publication);
    }


    @Override
    public void deletePublication(Long id){
        publicationRepository.deleteById(id);
    }

    @Override
    public List<Publication> subscriberPublications(User profile) {

        List<Publication> publications = new ArrayList<>();
        for(User friend : profile.getFriends()){
            publications.addAll(friend.getPublications());
        }
        publications.sort(Comparator.comparing(Publication::getDateOfCreate));
        return publications;
    }
}
