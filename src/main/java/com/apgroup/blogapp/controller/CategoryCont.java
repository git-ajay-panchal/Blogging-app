package com.apgroup.blogapp.controller;

import com.apgroup.blogapp.dto.CategoryDto;
import com.apgroup.blogapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryCont {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCat(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto cateDtoCreated = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(cateDtoCreated, HttpStatus.CREATED);
    }

    @PostMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCat(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
        CategoryDto updateCategory = categoryService.updateCategory(categoryDto,catId);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/{delCatId}")
    public ResponseEntity<?> deleteCat(@PathVariable Integer delCatId){
        categoryService.deleteCategory(delCatId);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        CategoryDto categoryDto = categoryService.getCategoryById(catId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDtoList,HttpStatus.OK);
    }

}
