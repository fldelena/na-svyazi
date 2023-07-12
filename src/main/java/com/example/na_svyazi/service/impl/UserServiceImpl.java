package com.example.na_svyazi.service.impl;

import com.example.na_svyazi.dao.InviteRepository;
import com.example.na_svyazi.dao.UserRepository;
import com.example.na_svyazi.entity.Image;
import com.example.na_svyazi.entity.Invite;
import com.example.na_svyazi.service.UserService;
import com.example.na_svyazi.entity.User;
import com.example.na_svyazi.entity.enums.Role;
import com.example.na_svyazi.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final InviteRepository inviteRepository;
    private final PasswordEncoder passwordEncoder;

    //Сделать тут выбрасывание ошибки, если пользовательу уже существует
    //И если не удалось получить изображение
    @Override
    public boolean createUser(MultipartFile file, User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        Image image;
        if (file != null) {
            try {
                image = ImageUtil.fileToImage(file);
                user.setAvatar(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        userRepository.save(user);
        return true;
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user;
    }

    @Override
    public List<User> getUsersByName(String name) {
        List<User> users;
        if (name == null || name.equals(""))
            users = userRepository.findAll();
        else {
            users = userRepository.findByName(name);
        }
        return users;
    }

    @Override
    public void addFriendToUser(Long id, User profile) {
        System.out.println("2");
        User user = userRepository.findById(id).orElseThrow();
        profile.addFriend(user);
        //ниже проблема
        userRepository.save(profile);
        System.out.println("5");
    }

    @Override
    public void addFriendToUserByInvite(Long id, User profile) {
        Invite invite = inviteRepository.findById(id).orElseThrow();
        User user = invite.getFromUser();
        profile.addFriend(user);
        profile.getInvites().remove(invite);
        inviteRepository.delete(invite);
        userRepository.save(profile);
    }

    @Override
    public void unfriend(Long id, User profile) {
        Invite invite = inviteRepository.findById(id).orElseThrow();
        profile.getInvites().remove(invite);
        inviteRepository.delete(invite);
        userRepository.save(profile);
    }

    @Override
    public void deleteFriendToUser(Long id, User profile) {
        User user = userRepository.findById(id).orElseThrow();
        for (Invite invite : user.getInvites()) {
            if (invite.getFromUser() == profile) {
                user.getInvites().remove(invite);
                inviteRepository.delete(invite);
                break;
            }
        }
        profile.deleteFriend(user);
        userRepository.save(profile);
    }

    @Override
    public List<User> showFriendList(User profile) {
        return profile.getFriends().stream()
                .filter(o -> o.getFriends().contains(profile)).toList();
    }

    @Override
    public List<User> showSubscriptionList(User profile) {
        return profile.getFriends().stream()
                .filter(o -> !o.getFriends().contains(profile)).toList();

    }

    @Override
    public List<User> showSubscriberList(User profile) {
        return profile.getSubscribers();
    }


}
