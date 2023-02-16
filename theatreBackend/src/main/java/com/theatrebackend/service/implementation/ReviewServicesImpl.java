package com.theatrebackend.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theatrebackend.dto.review.Message;
import com.theatrebackend.dto.review.Rate;
import com.theatrebackend.repository.MessageRepository;
import com.theatrebackend.repository.RateRepository;
import com.theatrebackend.service.ReviewServices;

@Service
public class ReviewServicesImpl implements ReviewServices {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Optional<Rate> addRate(Rate rate) {
        return Optional.of(rateRepository.save(rate));
    }

    @Override
    public Optional<Message> addMessage(Message message) {
        return Optional.of(messageRepository.save(message));
    }
}