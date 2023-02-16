package com.theatrebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatrebackend.dto.review.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

}
