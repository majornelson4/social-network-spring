package com.dadr.socialnetwork.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CommentDto(
        Integer id,
        @NotBlank(message = "Content cannot be null nor empty")
        @Size(min = 5, max = 300, message = "Required min length:5 and max length:300")
        String content,
        LocalDate publishedDate) {
}
