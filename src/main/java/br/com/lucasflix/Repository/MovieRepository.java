package br.com.lucasflix.Repository;

import br.com.lucasflix.entity.Category;
import br.com.lucasflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByCategories (List<Category> categories);
}
