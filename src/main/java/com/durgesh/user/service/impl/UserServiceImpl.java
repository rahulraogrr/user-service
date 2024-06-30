package com.durgesh.user.service.impl;

import com.durgesh.user.entity.User;
import com.durgesh.user.exception.ResourceNotFoundException;
import com.durgesh.user.repository.UserRepository;
import com.durgesh.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId))
                .orElseThrow(()-> new ResourceNotFoundException("User with id " + userId + " not found"));
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.findById(UUID.fromString(userId))
                .ifPresent(user -> userRepository.deleteById(UUID.fromString(String.valueOf(user.getUserId()))));
    }

    @Override
    public User updateUser(User user) {
        User userToUpdate = getUserById(String.valueOf(user.getUserId()));
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setMiddleName(user.getMiddleName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUserRole(user.getUserRole());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setDateOfBirth(user.getDateOfBirth());
        userToUpdate.setGender(user.getGender());
        userToUpdate.setAbout(user.getAbout());
        return userRepository.save(userToUpdate);
    }

}