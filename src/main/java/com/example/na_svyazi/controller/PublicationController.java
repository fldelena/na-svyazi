package com.example.na_svyazi.controller;

import com.example.na_svyazi.service.UserService;
import com.example.na_svyazi.entity.Publication;
import com.example.na_svyazi.entity.User;
import com.example.na_svyazi.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/publication")
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;
    private final UserService userService;


    @GetMapping("/page")
    public String showAllPublications(Principal principal, Model model) {
        User profile = userService.getUserByPrincipal(principal);
        List<Publication> publications = publicationService.subscriberPublications(profile);
        model.addAttribute("publications", publications);
        model.addAttribute("profile", profile);
        return "main-page";
    }

    @GetMapping("/info/{id}")
    public String publicationInfo(@PathVariable Long id, Model model){
        Publication publication = publicationService.getPublicationById(id);
        model.addAttribute("publication", publication);
        model.addAttribute("images", publication.getImages());
        return "publication-info";
    }

    @PostMapping("/save")
    public String savePublication(@RequestParam("file") MultipartFile file, Publication publication, Principal principal){
        User profile = userService.getUserByPrincipal(principal);
        publicationService.savePublication(profile, publication, file);
        return "redirect:/user/info/" + profile.getId();
    }

    @PostMapping("/delete/{id}")
    public String deletePublication(@PathVariable Long id){
        publicationService.deletePublication(id);
        return "redirect:/publication/all";
    }
}
