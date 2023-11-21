package com.dadr.socialnetwork.controller;

import com.dadr.socialnetwork.dto.CommentDto;
import com.dadr.socialnetwork.service.CommentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/posts/{postId}/comments")
public class CommentController {
    CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> findCommentById(@PathVariable Integer postId, @PathVariable Integer commentId) {
        CommentDto commentDto = commentService.findCommentById(postId, commentId);
        return ResponseEntity.ok(commentDto);
    }
    @GetMapping
    public ResponseEntity<Set<CommentDto>> findAllComments(@PathVariable Integer postId) {
        return ResponseEntity.ok(commentService.findAllComments(postId));
    }
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable Integer postId, @Valid @RequestBody CommentDto commentRequest) {
        CommentDto commentDto = commentService.createComment(postId, commentRequest);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer postId, @PathVariable Integer commentId, @Valid @RequestBody CommentDto commentRequest) {
        CommentDto commentDto = commentService.updateComment(postId, commentId, commentRequest);
        return ResponseEntity.ok(commentDto);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer postId, @PathVariable Integer commentId) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment was successfully deleted");
    }
}
