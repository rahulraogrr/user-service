package com.durgesh.user.service;

import com.durgesh.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(String userId);
    void deleteUserById(String userId);
}