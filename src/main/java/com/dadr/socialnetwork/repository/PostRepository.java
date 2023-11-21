package com.dadr.socialnetwork.repository;

import com.dadr.socialnetwork.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    List<Post> findAll();
}
