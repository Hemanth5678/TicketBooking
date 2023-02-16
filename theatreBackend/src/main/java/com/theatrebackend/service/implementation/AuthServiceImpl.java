package com.theatrebackend.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.theatrebackend.dto.user.User;
import com.theatrebackend.repository.UserRepository;
import com.theatrebackend.service.AuthService;


/**
 * @author Hemanthav
 *
 */
@Component
public class AuthServiceImpl implements UserDetailsService, AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String getCurrentLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found for email: " + email);

        return UserDetailsImpl.build(user);
    }


    @Override
    public boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass()))
            return false;

        return authentication.isAuthenticated();
    }
}