package com.theatrebackend.controller;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatrebackend.dto.cinema.Theatre;
import com.theatrebackend.payload.response.ApiResponse;
import com.theatrebackend.service.TheatreServices;


@RestController
@RequestMapping("/api/hall")
public class TheatreRestController {

    @Autowired
    private TheatreServices hallServices;

    @GetMapping
    public ResponseEntity<?> getHall() {
        Optional<Collection<Theatre>> existedHall = hallServices.getAllHalls();
        return ResponseEntity.status(HttpStatus.OK).body(existedHall);
    }
    
    @PostMapping
    public ResponseEntity<?> addHall(@RequestBody Theatre hall) {
        Optional<Theatre> existedHall = hallServices.getHallByName(hall.getName());

        if (existedHall.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Hall already exist for Name: " + hall.getName(), ""));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Hall Added successfully", hallServices.addHall(hall)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHall(@RequestBody Theatre hall) {
        Optional<Theatre> existedHall = hallServices.getHallByName(hall.getId());

        if (existedHall.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Hall not found", hall));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Hall updated successfully", hallServices.updateHall(hall)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHall(@PathVariable String id) {
        Optional<Theatre> existedHall = hallServices.getHallByName(id);

        if (existedHall.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                    HttpStatus.BAD_REQUEST.value(), LocalDateTime.now().toString(),
                    "Hall not found for id: " + id, ""));

        hallServices.deleteHallById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                HttpStatus.OK.value(), LocalDateTime.now().toString(),
                "Hall deleted successfully", ""));
    }

}
