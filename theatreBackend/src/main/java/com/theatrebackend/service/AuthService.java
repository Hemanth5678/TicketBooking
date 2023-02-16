package com.theatrebackend.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails loadUserByUsername(String email);

    String getCurrentLoggedUser();

    boolean isUserAuthenticated();
}
