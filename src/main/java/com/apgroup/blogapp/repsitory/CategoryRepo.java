package com.apgroup.blogapp.repsitory;

import com.apgroup.blogapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
