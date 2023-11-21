package com.dadr.socialnetwork.repository;

import com.dadr.socialnetwork.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
