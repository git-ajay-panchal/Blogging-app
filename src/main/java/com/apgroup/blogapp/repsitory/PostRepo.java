package com.apgroup.blogapp.repsitory;

import com.apgroup.blogapp.entities.Category;
import com.apgroup.blogapp.entities.Post;
import com.apgroup.blogapp.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    // you can create these methods in service also, but you have to implement then

    // finding all post by user, custom finder method
    List<Post> findByUser(User user, Pageable pageable);
    List<Post> findByCategory(Category category);

    //Find posts by title
    List<Post> findByTitle(String title);

    //    findByTitleContaining(String title); //  LIKE " " query
    // this above method might have issues in hibernate core 5.6.6/.7 version
    // so we are using query
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String keyword);

}
