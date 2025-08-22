package br.com.lucasflix.Controller;

import br.com.lucasflix.Controller.Request.CategoryRequest;
import br.com.lucasflix.Controller.Response.CategoryResponse;
import br.com.lucasflix.Mapper.CategoryMapper;
import br.com.lucasflix.Service.CategoryService;
import br.com.lucasflix.entity.Category;
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
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "Gerenciamento de categorias de filmes")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Listar todas as categorias")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/list")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        List<CategoryResponse> responses = categories.stream()
                .map(category -> CategoryMapper.categoryResponse(category))
                .toList();
        return ResponseEntity.ok(responses);

    }

    @Operation(summary = "Criar uma nova categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = CategoryMapper.toCategory(categoryRequest);
        Category savedCategory = categoryService.newCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryMapper.categoryResponse(savedCategory));
    }

    @Operation(summary = "Buscar categoria por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/list/{id}")
    public ResponseEntity<CategoryResponse> findByID(@PathVariable Long id) {
        return categoryService.categoryFindByID(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.categoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Excluir categoria por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoria excluída"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteCategoryFindByID(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
