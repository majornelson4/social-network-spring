package com.dadr.socialnetwork.service;

import com.dadr.socialnetwork.dto.PostDto;
import com.dadr.socialnetwork.entity.Post;
import com.dadr.socialnetwork.exception.ResourceNotFoundException;
import com.dadr.socialnetwork.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.dadr.socialnetwork.mapper.PostMapper.MAPPER;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    private Post validateExistingPost(Integer postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post id not found"));
    }

    public PostDto createPost(PostDto postDto) {
        Post post = MAPPER.mapToEntity(postDto);
        post.setPublishedDate(LocalDateTime.now());
        Post savedPost = postRepository.save(post);
        return MAPPER.mapToDto(savedPost);
    }

    public PostDto updatePost(Integer postId, PostDto postDto) {
        Post post = validateExistingPost(postId);

        post.setImage(postDto.image());
        post.setContent(postDto.content());

        Post newPost = postRepository.save(post);
        return MAPPER.mapToDto(newPost);
    }

    public void deletePost(Integer postId) {
        Post post = validateExistingPost(postId);
        postRepository.delete(post);
    }

    public PostDto findPostById(Integer postId) {
        Post post = validateExistingPost(postId);
        return MAPPER.mapToDto(post);
    }

    public List<PostDto> findAllPosts() {
        return postRepository.findAll().stream().map(MAPPER::mapToDto).collect(Collectors.toList());
    }
}
