package com.theatrebackend.service.implementation;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.theatrebackend.dto.enums.EROLE;
import com.theatrebackend.dto.user.User;
import com.theatrebackend.repository.RoleRepository;
import com.theatrebackend.repository.UserRepository;
import com.theatrebackend.service.UserServices;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Collection<User>> getAllUsers() {
        return Optional.ofNullable(userRepository.findAll());
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(userRepository.findUserById(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findUserByEmail(email));
    }

    @Override
    public Optional<User> addUser(User user) {
        user.getRoles().add(roleRepository.findRoleByRoleName(EROLE.ROLE_USER));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> updateUser(User user) {
        return Optional.of(userRepository.save(user));
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
}