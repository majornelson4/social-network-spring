package com.dadr.socialnetwork.service;

import com.dadr.socialnetwork.dto.CommentDto;
import com.dadr.socialnetwork.entity.Comment;
import com.dadr.socialnetwork.entity.Post;
import com.dadr.socialnetwork.exception.ApplicationApiException;
import com.dadr.socialnetwork.exception.ResourceNotFoundException;
import com.dadr.socialnetwork.repository.CommentRepository;
import com.dadr.socialnetwork.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dadr.socialnetwork.mapper.CommentMapper.MAPPER;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;

    private Post validatePost(Integer postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post id not found"));
    }

    private Comment validatePostAndComment(Integer postId, Integer commentId) {
        Post post = validatePost(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment id not found"));
        if(!comment.getPost().getId().equals(post.getId())) {
            throw new ApplicationApiException("Post and Comment do not relate to each other");
        }
        return comment;
    }

    public CommentDto createComment(Integer postId, CommentDto commentDto) {
        Post post = validatePost(postId);
        Comment comment = MAPPER.mapToEntity(commentDto);
        comment.setPublishedDate(LocalDate.now());
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return MAPPER.mapToDto(savedComment);
    }

    public CommentDto updateComment(Integer postId, Integer commentId, CommentDto commentDto) {
        Comment comment = validatePostAndComment(postId, commentId);
        comment.setContent(commentDto.content());
        Comment savedComment = commentRepository.save(comment);
        return MAPPER.mapToDto(savedComment);
    }
    public void deleteComment(Integer postId, Integer commentId) {
        Comment comment = validatePostAndComment(postId, commentId);
        commentRepository.delete(comment);
    }
    public CommentDto findCommentById(Integer postId, Integer commentId) {
        Comment comment = validatePostAndComment(postId, commentId);
        return MAPPER.mapToDto(comment);
    }
    public Set<CommentDto> findAllComments(Integer postId) {
        Post post = validatePost(postId);
        return post.getComments().stream().map(MAPPER::mapToDto).collect(Collectors.toSet());
    }
}
