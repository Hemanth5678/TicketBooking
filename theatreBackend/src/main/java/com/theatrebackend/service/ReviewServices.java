package com.theatrebackend.service;

import java.util.Optional;

import com.theatrebackend.dto.review.Message;
import com.theatrebackend.dto.review.Rate;


public interface ReviewServices {

    Optional<Rate> addRate(Rate rate);

    Optional<Message> addMessage(Message message);
}
