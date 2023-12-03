package com.dadr.socialnetwork.dto;

import java.util.List;

public record ReadUserDto(String username,
                          List<PostDto> posts) {
}
