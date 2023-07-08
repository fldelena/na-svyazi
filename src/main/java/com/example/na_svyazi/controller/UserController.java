package com.example.na_svyazi.controller;

import com.example.na_svyazi.entity.User;
import com.example.na_svyazi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/search/")
    public String userSearch(@RequestParam(value = "name", required = false) String name, Principal principal, Model model){
        User profile = userService.getUserByPrincipal(principal);
        List<User> users = userService.getUsersByName(name);
        model.addAttribute("users", users);
        model.addAttribute("profile", profile);
        return "user-search";
    }

    @GetMapping("/info/{id}")
    public String userInfo(@PathVariable Long id, Principal principal, Model model){
        User profile = userService.getUserByPrincipal(principal);
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("profile", profile);
        model.addAttribute("isOwner", profile == user);
        model.addAttribute("isFriend", profile.getFriends().contains(user));
        return "user-info";
    }

    @GetMapping("/add-friend/{id}")
    public String addFriend(@PathVariable Long id, Principal principal){
        User profile = userService.getUserByPrincipal(principal);
        userService.addFriendToUser(id, profile);
        return "redirect:/user/info/" + id;

    }

    @GetMapping("/delete-friend/{id}")
    public String deleteFriend(@PathVariable Long id, Principal principal){
        User profile = userService.getUserByPrincipal(principal);
        userService.deleteFriendToUser(id, profile);
        return "redirect:/user/info/" + id;
    }


    @GetMapping("/friends/")
    public String friends(Principal principal, Model model){
        User profile = userService.getUserByPrincipal(principal);
        List<User> friends = userService.showFriendList(profile);
        model.addAttribute("friends", friends);
        model.addAttribute("profile", profile);
        return"friends";
    }
    //Подписки
    @GetMapping("/subscriptions/")
    public String subscriptions(Principal principal, Model model){
        User profile = userService.getUserByPrincipal(principal);
        List<User> subscriptions = userService.showSubscriptionList(profile);
        model.addAttribute("subscriptions", subscriptions);
        model.addAttribute("profile", profile);
        return "subscriptions";
    }
    //Подписчики
    @GetMapping("/subscribers/")
    public String subscribers(Principal principal, Model model){
        User profile = userService.getUserByPrincipal(principal);
        List<User> subscribers = userService.showSubscriberList(profile);
        model.addAttribute("subscribers", subscribers);
        model.addAttribute("profile", profile);
        return "subscribers";
    }





}
