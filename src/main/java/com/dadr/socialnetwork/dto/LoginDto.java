package com.dadr.socialnetwork.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "Username is mandatory")
        String username,
        @NotBlank(message = "Username is mandatory")
        String password) {

}
