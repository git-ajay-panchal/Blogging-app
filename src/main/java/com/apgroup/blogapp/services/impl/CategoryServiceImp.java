package com.apgroup.blogapp.services.impl;

import com.apgroup.blogapp.dto.CategoryDto;
import com.apgroup.blogapp.entities.Category;
import com.apgroup.blogapp.exceptions.ResourceNotFoundException;
import com.apgroup.blogapp.repsitory.CategoryRepo;
import com.apgroup.blogapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryRepo.save(cateDtoToCate(categoryDto));
        return cateToCateDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() ->
                new ResourceNotFoundException("cat","id",catId));

        category.setCatTitle(categoryDto.getCatTitle());
        category.setCatDesc(categoryDto.getCatDesc());
        Category updatedCategory = categoryRepo.save(category);
        return cateToCateDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() ->
                new ResourceNotFoundException("cat","id",catId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() ->
                new ResourceNotFoundException("cat","id",catId));
        return cateToCateDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = categories.stream()
                .map(this::cateToCateDto)  // replaced lambda with method ref.: map(category -> cateToCateDto(category))
                .collect(Collectors.toList());
//    List<CategoryDto> categoryDtoList = categories.stream().map(category -> cateToCateDto(category)).collect(Collectors.toList());

        return categoryDtoList;
    }

    public Category cateDtoToCate(CategoryDto categoryDto){
        return modelMapper.map(categoryDto,Category.class);
    }

    public CategoryDto cateToCateDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }
}
