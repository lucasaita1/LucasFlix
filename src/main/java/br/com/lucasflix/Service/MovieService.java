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
