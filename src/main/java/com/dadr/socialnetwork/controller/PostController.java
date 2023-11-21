package com.dadr.socialnetwork.controller;

import com.dadr.socialnetwork.dto.PostDto;
import com.dadr.socialnetwork.service.PostService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostController {
    PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postRequest) {
        PostDto postDto = postService.createPost(postRequest);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @Valid @RequestBody PostDto postRequest) {
        PostDto postDto = postService.updatePost(postId, postRequest);
        return ResponseEntity.ok(postDto);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> findAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> findPostById(@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.findPostById(postId));
    }
}
