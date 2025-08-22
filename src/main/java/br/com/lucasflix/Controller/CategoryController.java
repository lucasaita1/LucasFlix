package br.com.lucasflix.Controller;

import br.com.lucasflix.Controller.Request.CategoryRequest;
import br.com.lucasflix.Controller.Response.CategoryResponse;
import br.com.lucasflix.Mapper.CategoryMapper;
import br.com.lucasflix.Service.CategoryService;
import br.com.lucasflix.entity.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        List<CategoryResponse> responses = categories.stream()
                .map(category -> CategoryMapper.categoryResponse(category))
                .toList();
        return ResponseEntity.ok(responses);

    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = CategoryMapper.toCategory(categoryRequest);
        Category savedCategory = categoryService.newCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryMapper.categoryResponse(savedCategory));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<CategoryResponse> findByID(@PathVariable Long id) {
        return categoryService.categoryFindByID(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.categoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteCategoryFindByID(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
