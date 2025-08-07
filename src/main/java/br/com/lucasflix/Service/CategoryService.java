package br.com.lucasflix.Service;

import br.com.lucasflix.Repository.CategoryRepository;
import br.com.lucasflix.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category newCategory(Category category) {
        return repository.save(category);
    }

    public Optional<Category> categoryFindByID(Long id) {
        return repository.findById(id);
    }

    public void deleteCategoryFindByID(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }

    }

}
