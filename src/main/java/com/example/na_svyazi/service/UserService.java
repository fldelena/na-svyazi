package com.example.na_svyazi.service;

import com.example.na_svyazi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface UserService {

    boolean createUser(MultipartFile file, User user);

    User getUserByPrincipal(Principal principal);

    User getUserById(Long id);

    List<User> getUsersByName(String name);

    void addFriendToUser(Long id, User profile);

    void addFriendToUserByInvite(Long id, User profile);

    void unfriend(Long id, User profile);

    void deleteFriendToUser(Long id, User profile);

    List<User> showFriendList(User profile);

    List<User> showSubscriptionList(User profile);

    List<User> showSubscriberList(User profile);
}
