package br.com.lucasflix.Service;

import br.com.lucasflix.Repository.MovieRepository;
import br.com.lucasflix.entity.Category;
import br.com.lucasflix.entity.Movie;
import br.com.lucasflix.entity.Streaming;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save (Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreaming(movie.getStreamings()));
        return repository.save(movie);
    }

    public List<Movie> findAll(){
        return repository.findAll();
    }

    public Optional<Movie> findById (Long id){
        return repository.findById(id);
    }

    public Optional<Movie> update(Long moveId, Movie movie){
        Optional<Movie> optionalMovie = repository.findById(moveId);

        if (optionalMovie.isPresent()){

            List<Category> categories = this.findCategories(movie.getCategories());
            List<Streaming> streamings = this.findStreaming(movie.getStreamings());

            Movie movies = optionalMovie.get();
            movies.setTitle(movie.getTitle());
            movies.setDescription(movie.getDescription());
            movies.setReleaseDate(movie.getReleaseDate());
            movies.setRating(movie.getRating());

            movies.getCategories().clear();
            movies.getCategories().addAll(categories);

            movies.getStreamings().clear();
            movies.getStreamings().addAll(streamings);

            repository.save(movie);
            return Optional.of(movie);
        }
        return Optional.empty();
    }

    public List<Movie> findByCategories (Long categoryId){
        return repository.findByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    // modern method
    private List<Category> findCategories(List<Category> categories){
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> categoryService.categoryFindByID(category.getId())
                .ifPresent(categoriesFound::add));
        return categoriesFound;

    }

    // classic method
    private List<Streaming> findStreaming(List<Streaming> streamings){
        List<Streaming> streamingsFound = new ArrayList<>();
        for (Streaming streaming : streamings){
            streamingService.streamingFindById(streaming.getId())
                    .ifPresent(streamingsFound::add);
        }
        return streamingsFound;
    }
}
