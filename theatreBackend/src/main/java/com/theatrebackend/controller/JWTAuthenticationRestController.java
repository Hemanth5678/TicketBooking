package com.theatrebackend.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatrebackend.dto.user.User;
import com.theatrebackend.payload.request.JWTRequest;
import com.theatrebackend.payload.response.ApiResponse;
import com.theatrebackend.payload.response.JWTResponse;
import com.theatrebackend.security.jwt.JWTUtil;
import com.theatrebackend.service.UserServices;


@CrossOrigin
@RestController
@RequestMapping("/api/auth/login")
public class JWTAuthenticationRestController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping()
    public ResponseEntity<?> loginHandler(@RequestBody JWTRequest body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authenticationManager.authenticate(authInputToken);
            String jwtToken = jwtUtil.generateToken(body.getEmail());

            Optional<User> loggedUser = userServices.getUserByEmail(body.getEmail());
            assert loggedUser.isPresent();

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.OK.value(), LocalDateTime.now().toString(),
                    "User logged in successfully", new JWTResponse(jwtToken, loggedUser.get())));

        } catch (AuthenticationException authExc) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.NOT_FOUND.value(), LocalDateTime.now().toString(), "Invalid email or password", authExc));
        }
    }

}