package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository repository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository repository, GenreRepository genreRepository) {
        this.repository = repository;
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long movieId) {
        Optional<Movie> obj = repository.findById(movieId);
        Movie movie = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new MovieDTO(movie);
    }

    @Transactional(readOnly = true)
    public Page<MovieDTO> findByGenre(Long genreId, Pageable pageable) {
        Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
        Page<Movie> page = repository.findByGenre(genre, pageable);
        repository.findMoviesAndGenres(page.getContent());

        return page.map(MovieDTO::new);
    }
}
