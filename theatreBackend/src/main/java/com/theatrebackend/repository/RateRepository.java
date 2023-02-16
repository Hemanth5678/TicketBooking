package com.theatrebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatrebackend.dto.review.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, String> {

}
