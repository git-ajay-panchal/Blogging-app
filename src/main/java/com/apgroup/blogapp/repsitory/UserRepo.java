package com.apgroup.blogapp.repsitory;

import com.apgroup.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
