package com.apgroup.blogapp.repsitory;

import com.apgroup.blogapp.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment , Integer> {

}
