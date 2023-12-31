package com.dadr.socialnetwork.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.Set;

public record PostDto(Integer id,
                      @Size(max = 2000)
                      String image,
                      @NotBlank(message = "Content cannot be null nor empty")
                      @Size(min = 5, max = 300, message = "Required min length:5 and max length:300")
                      String content,
                      LocalDateTime publishedDate,
                      Set<CommentDto> comments) {
}
