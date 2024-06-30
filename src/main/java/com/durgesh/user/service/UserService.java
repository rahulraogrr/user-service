package com.durgesh.user.service;

import com.durgesh.user.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(String userId);
    void deleteUserById(String userId);
    User updateUser(User user);
}