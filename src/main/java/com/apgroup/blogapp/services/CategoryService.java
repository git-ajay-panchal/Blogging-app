package com.apgroup.blogapp.services;

import com.apgroup.blogapp.dto.CategoryDto;
import java.util.List;


public interface CategoryService {
    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //Update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer catId);

    //delete
    void deleteCategory(Integer catId);

    //get
    CategoryDto getCategoryById(Integer catId);

    //getAll
    List<CategoryDto> getAllCategories();
}
