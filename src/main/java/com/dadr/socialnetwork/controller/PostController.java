package com.dadr.socialnetwork.controller;

import com.dadr.socialnetwork.dto.PostDto;
import com.dadr.socialnetwork.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postRequest) {
        PostDto postDto = postService.createPost(postRequest);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@PathVariable Integer postId, @Valid @RequestBody PostDto postRequest) {
        return postService.updatePost(postId, postRequest);
    }
    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return "Post deleted successfully";
    }
    @GetMapping
    public List<PostDto> findAllPosts() {
        return postService.findAllPosts();
    }
    @GetMapping("/{postId}")
    public PostDto findPostById(@PathVariable Integer postId) {
        return postService.findPostById(postId);
    }
}
