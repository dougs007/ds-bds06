package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;
    private final ReviewService reviewService;

    public MovieController(MovieService service, ReviewService reviewService) {
        this.service = service;
        this.reviewService = reviewService;
    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findByGenre(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        Page<MovieDTO> pageMovies = service.findByGenre(genreId, pageable);
        return ResponseEntity.ok().body(pageMovies);
    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> findById(@PathVariable("movieId") Long movieId) {
        MovieDTO dto = service.findById(movieId);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{movieId}/reviews")
    public ResponseEntity<List<ReviewDTO>> findMovieReviews(@PathVariable Long movieId) {
        List<ReviewDTO> reviews = reviewService.findByMovie(movieId);
        return ResponseEntity.ok(reviews);
    }
}
