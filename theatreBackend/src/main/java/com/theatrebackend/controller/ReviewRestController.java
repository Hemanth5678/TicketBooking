package com.theatrebackend.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatrebackend.dto.review.Message;
import com.theatrebackend.dto.review.Rate;
import com.theatrebackend.payload.response.ApiResponse;
import com.theatrebackend.service.ReviewServices;

@RestController
@RequestMapping("/api/review")
public class ReviewRestController {

    @Autowired
    private ReviewServices reviewServices;

    @PostMapping("/rate")
    public ResponseEntity<?> addRate(@RequestBody Rate rate) {
        Optional<Rate> savedRate = reviewServices.addRate(rate);

        if (savedRate.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Field to submit rate", ""));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Rate submitted successfully", savedRate));
    }

    @PostMapping("/message")
    public ResponseEntity<?> addMessage(@RequestBody Message message) {
        Optional<Message> savedMessage = reviewServices.addMessage(message);

        if (savedMessage.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Field to submit message", ""));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Message submitted successfully", savedMessage));
    }
}

