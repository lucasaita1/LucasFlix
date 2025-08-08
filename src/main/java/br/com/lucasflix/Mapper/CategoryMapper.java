package br.com.lucasflix.Mapper;

import br.com.lucasflix.Controller.Request.CategoryRequest;
import br.com.lucasflix.Controller.Response.CategoryResponse;
import br.com.lucasflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory (CategoryRequest categoryRequest){
        return Category
                .builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse categoryResponse (Category category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
