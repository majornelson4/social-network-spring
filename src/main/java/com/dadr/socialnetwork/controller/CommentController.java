package com.dadr.socialnetwork.controller;

import com.dadr.socialnetwork.dto.CommentDto;
import com.dadr.socialnetwork.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public CommentDto findCommentById(@PathVariable Integer postId, @PathVariable Integer commentId) {
        return commentService.findCommentById(postId, commentId);
    }
    @GetMapping
    public Set<CommentDto> findAllCommentsByPostId(@PathVariable Integer postId) {
        return commentService.findAllComments(postId);
    }
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable Integer postId, @Valid @RequestBody CommentDto commentRequest) {
        CommentDto commentDto = commentService.createComment(postId, commentRequest);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }
    @PutMapping("/{commentId}")
    public CommentDto updateComment(@PathVariable Integer postId, @PathVariable Integer commentId, @Valid @RequestBody CommentDto commentRequest) {
        return commentService.updateComment(postId, commentId, commentRequest);
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Integer postId, @PathVariable Integer commentId) {
        commentService.deleteComment(postId, commentId);
        return "Comment was successfully deleted";
    }

}
