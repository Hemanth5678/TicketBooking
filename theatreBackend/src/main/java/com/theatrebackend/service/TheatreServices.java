package com.theatrebackend.service;

import java.util.Collection;
import java.util.Optional;

import com.theatrebackend.dto.cinema.Theatre;
import com.theatrebackend.dto.cinema.Show;


public interface TheatreServices {

    Optional<Collection<Theatre>> getAllHalls();

    Optional<Collection<Theatre>> getAllShowHalls();

    Optional<Collection<Theatre>> getAllIdleHalls();

    Optional<Theatre> getHallByName(String name);

    Optional<Theatre> getHallByShow(Show show);

    Optional<Theatre> getHallById(String id);

    Optional<Theatre> addHall(Theatre hall);

    Optional<Theatre> updateHall(Theatre hall);

    void deleteHallById(String id);
}
