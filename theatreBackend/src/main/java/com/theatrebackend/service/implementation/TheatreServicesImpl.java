package com.theatrebackend.service.implementation;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theatrebackend.dto.cinema.Theatre;
import com.theatrebackend.dto.cinema.Show;
import com.theatrebackend.repository.TheatreRepository;
import com.theatrebackend.service.TheatreServices;


@Service
public class TheatreServicesImpl implements TheatreServices {

    @Autowired
    private TheatreRepository hallRepository;

    @Override
    public Optional<Collection<Theatre>> getAllHalls() {
        return Optional.ofNullable(hallRepository.findAll());
    }

    @Override
    public Optional<Collection<Theatre>> getAllShowHalls() {
        Collection<Theatre> halls = hallRepository.findAll();
        Predicate<Theatre> showHallsPredicate = hall -> hall.getShows() != null;

        if (!halls.isEmpty())
            return Optional.of(halls.stream().filter(showHallsPredicate).collect(Collectors.toList()));

        return Optional.empty();
    }

    @Override
    public Optional<Collection<Theatre>> getAllIdleHalls() {
        Collection<Theatre> halls = hallRepository.findAll();
        Predicate<Theatre> idleHallsPredicate = hall -> hall.getShows() == null;

        if (!halls.isEmpty())
            return Optional.of(halls.stream().filter(idleHallsPredicate).collect(Collectors.toList()));

        return Optional.empty();
    }

    @Override
    public Optional<Theatre> getHallById(String id) {
        return Optional.ofNullable(hallRepository.findHallById(id));
    }

    @Override
    public Optional<Theatre> getHallByName(String name) {
        return Optional.ofNullable(hallRepository.findHallByName(name));
    }

    @Override
    public Optional<Theatre> getHallByShow(Show show) {
        Collection<Theatre> halls = hallRepository.findAll();

        for (Theatre hall : halls) {
            for (Show tempShow : hall.getShows()) {
                if (Objects.equals(tempShow.getId(), show.getId()))
                    return Optional.of(hall);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Theatre> addHall(Theatre hall) {
        return Optional.of(hallRepository.save(hall));
    }

    @Override
    public Optional<Theatre> updateHall(Theatre hall) {
        return Optional.of(hallRepository.save(hall));
    }

    @Override
    public void deleteHallById(String id) {
        hallRepository.deleteById(id);
    }
}

