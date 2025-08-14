package br.com.lucasflix.Controller;


import br.com.lucasflix.Controller.Request.MovieRequest;
import br.com.lucasflix.Controller.Response.MovieResponse;
import br.com.lucasflix.Mapper.MovieMapper;
import br.com.lucasflix.Service.MovieService;
import br.com.lucasflix.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest movieRequest) {
        Movie movie = MovieMapper.toMovie(movieRequest);
        Movie saveMovie = service.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovieMapper.toMovieResponse(movie));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        List<Movie> movies = service.findAll();
        List<MovieResponse> responses = movies.stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList();
       return ResponseEntity.ok(responses);
    }

    @GetMapping
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return service.findById(id)
                .map(movie ->ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

}
