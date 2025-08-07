package br.com.lucasflix.Controller;

import br.com.lucasflix.Service.CategoryService;
import br.com.lucasflix.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public List<Category> getAllCategorys (){
        return categoryService.findAll();
    }

    @PostMapping("/create")
    public Category createCategory (@RequestBody Category category){
        return categoryService.newCategory(category);
    }

    @GetMapping("/list/{id}")
    public Category findByID (@PathVariable Long id){
        Optional<Category> optionalCategory = categoryService.categoryFindByID(id);
        if (optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById (@PathVariable Long id){
        categoryService.deleteCategoryFindByID(id);
    }

}
