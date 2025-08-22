package br.com.lucasflix.Controller;


import br.com.lucasflix.Controller.Request.MovieRequest;
import br.com.lucasflix.Controller.Response.MovieResponse;
import br.com.lucasflix.Mapper.MovieMapper;
import br.com.lucasflix.Service.MovieService;
import br.com.lucasflix.entity.Movie;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@Tag(name = "Filmes", description = "Gerenciamento de filmes")
public class MovieController {

    private final MovieService service;

    @Operation(summary = "Criar novo filme")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Filme criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest movieRequest) {
        Movie movie = MovieMapper.toMovie(movieRequest);
        Movie saveMovie = service.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovieMapper.toMovieResponse(movie));
    }

    @Operation(summary = "Listar todos os filmes")
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        List<Movie> movies = service.findAll();
        List<MovieResponse> responses = movies.stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList();
       return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Buscar filme por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme encontrado"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    @GetMapping ("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return service.findById(id)
                .map(movie ->ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar filme por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    @PutMapping ("/{id}")
    public ResponseEntity<MovieResponse> uptade(@PathVariable Long id, @Valid @RequestBody MovieRequest request){
        return service.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar filmes por categoria")
    @ApiResponse(responseCode = "200", description = "Lista de filmes da categoria retornada com sucesso")
    @GetMapping ("/search")
    public ResponseEntity<List<MovieResponse>> findByCateogroies (@RequestParam Long category){
        return ResponseEntity.ok(service.findByCategories(category)
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList());

    }

    @Operation(summary = "Excluir filme por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Filme excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @Operation(summary = "Top 5 melhores filmes")
    @ApiResponse(responseCode = "200", description = "Top 5 melhores filmes retornado com sucesso")
    @GetMapping ("/best")
    public ResponseEntity<List<MovieResponse>> top5BestMovies() {
        List<Movie> movies = service.top5BestMovies();
        List<MovieResponse> responses = movies.stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList();
        return ResponseEntity.ok(responses);
    }

}
