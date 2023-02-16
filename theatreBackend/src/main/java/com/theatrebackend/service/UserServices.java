package com.theatrebackend.service;

import java.util.Collection;
import java.util.Optional;

import com.theatrebackend.dto.user.User;


public interface UserServices {

    Optional<Collection<User>> getAllUsers();

    Optional<User> getUserById(String id);

    Optional<User> getUserByEmail(String email);

    Optional<User> addUser(User user);

    Optional<User> updateUser(User user);

    void deleteUserById(String id);
}
