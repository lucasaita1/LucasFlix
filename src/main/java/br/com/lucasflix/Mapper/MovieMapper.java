package br.com.lucasflix.Mapper;

import br.com.lucasflix.Controller.Request.MovieRequest;
import br.com.lucasflix.Controller.Response.CategoryResponse;
import br.com.lucasflix.Controller.Response.MovieResponse;
import br.com.lucasflix.Controller.Response.StreamingResponse;
import br.com.lucasflix.entity.Category;
import br.com.lucasflix.entity.Movie;
import br.com.lucasflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;



@UtilityClass
public class MovieMapper {

    public static Movie toMovie (MovieRequest request){

        List<Category> categoryList = request.categories()
                .stream()
                .map(categoryId ->Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamingList = request.streamings()
                .stream()
                .map(streamingId ->Streaming.builder().id(streamingId).build())
                .toList();


        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categoryList)
                .streamings(streamingList)
                .build();
    }

    public static MovieResponse toMovieResponse (Movie movie){

        List<CategoryResponse> cateroryList = movie.getCategories()
                .stream()
                .map(category -> CategoryMapper.categoryResponse(category))
                .toList();

        List<StreamingResponse> streamingList = movie.getStreamings()
                .stream()
                .map(streaming -> StreamigMapper.streamingResponse(streaming))
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(cateroryList)
                .streamings(streamingList)
                .build();
    }
}
