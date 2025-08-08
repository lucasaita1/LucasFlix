package br.com.lucasflix.Controller;

import br.com.lucasflix.Controller.Request.CategoryRequest;
import br.com.lucasflix.Controller.Response.CategoryResponse;
import br.com.lucasflix.Mapper.CategoryMapper;
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
    public List<CategoryResponse> getAllCategories (){
        List<Category> categories = categoryService.findAll();
        return categories.stream()
                .map(category -> CategoryMapper.categoryResponse(category))
                .toList();
    }

    @PostMapping("/create")
    public CategoryResponse createCategory (@RequestBody CategoryRequest categoryRequest){
       Category category = CategoryMapper.toCategory(categoryRequest);
       Category savedCategory = categoryService.newCategory(category);
        return CategoryMapper.categoryResponse(savedCategory);
    }

    @GetMapping("/list/{id}")
    public CategoryResponse findByID (@PathVariable Long id){
        Optional<Category> optionalCategory = categoryService.categoryFindByID(id);
        if (optionalCategory.isPresent()){
            return CategoryMapper.categoryResponse(optionalCategory.get());
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById (@PathVariable Long id){
        categoryService.deleteCategoryFindByID(id);
    }

}
