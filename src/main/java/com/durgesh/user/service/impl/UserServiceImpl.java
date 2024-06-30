package com.durgesh.user.service.impl;

import com.durgesh.user.dto.Hotel;
import com.durgesh.user.dto.Rating;
import com.durgesh.user.entity.User;
import com.durgesh.user.exception.ResourceNotFoundException;
import com.durgesh.user.repository.UserRepository;
import com.durgesh.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

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
        User dbUser = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        Rating[] userWithRating = restTemplate.getForObject("http://RATING-SERVICE/api/v1/ratings/users/" + dbUser.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.stream(userWithRating).toList();

        ratings.forEach(rating -> rating.setHotel(restTemplate.getForObject("http://HOTEL-SERVICE/api/v1/hotels/"+rating.getHotelId(), Hotel.class)));

        dbUser.setRatings(ratings);
        return dbUser;
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