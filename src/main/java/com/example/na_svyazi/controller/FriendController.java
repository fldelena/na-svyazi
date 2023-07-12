package com.example.na_svyazi.controller;


import com.example.na_svyazi.entity.Invite;
import com.example.na_svyazi.entity.User;
import com.example.na_svyazi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final UserService userService;

    @GetMapping("/all/")
    public String friends(Principal principal, Model model){
        User profile = userService.getUserByPrincipal(principal);
        List<User> friends = userService.showFriendList(profile);
        List<Invite> invites = profile.getInvites();
        model.addAttribute("friends", friends);
        model.addAttribute("invites", invites);
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

    @GetMapping("/add/{id}")
    public String addFriend(@PathVariable Long id, Principal principal){
        User profile = userService.getUserByPrincipal(principal);
        userService.addFriendToUserByInvite(id, profile);
        return "redirect:/friend/all/";
    }

    @GetMapping("/unfriend/{id}")
    public String unfriend(@PathVariable Long id, Principal principal){
        User profile = userService.getUserByPrincipal(principal);
        userService.unfriend(id, profile);
        return "redirect:/friend/all/";
    }

    @GetMapping("/delete/{id}")
    public String deleteFriend(@PathVariable Long id, Principal principal){
        User profile = userService.getUserByPrincipal(principal);
        userService.deleteFriendToUser(id, profile);
        return "redirect:/friend/all/";
    }


}
