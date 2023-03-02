package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ReviewDTO>> findAll(Pageable pageable) {
        Page<ReviewDTO> reviews = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReviewDTO> findById(@PathVariable Long id) {
        ReviewDTO reviewDto = service.findById(id);
        return ResponseEntity.ok().body(reviewDto);
    }

    @PreAuthorize("hasAnyRole('MEMBER')")
    @PostMapping
    public ResponseEntity<ReviewDTO> insert(@Valid @RequestBody ReviewDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReviewDTO> update(@PathVariable Long id, @RequestBody ReviewDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
