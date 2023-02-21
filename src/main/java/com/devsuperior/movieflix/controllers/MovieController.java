package com.devsuperior.movieflix.controllers;


import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> findById(@PathVariable("movieId") Long movieId) {
        MovieDTO dto = service.findById(movieId);
        return ResponseEntity.ok().body(dto);
    }
}
