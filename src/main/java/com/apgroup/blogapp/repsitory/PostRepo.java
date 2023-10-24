package com.apgroup.blogapp.repsitory;

import com.apgroup.blogapp.entities.Category;
import com.apgroup.blogapp.entities.Post;
import com.apgroup.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    // finding all post by user, custom finder method
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    // you can create these in service also, but you have to implement then
}
