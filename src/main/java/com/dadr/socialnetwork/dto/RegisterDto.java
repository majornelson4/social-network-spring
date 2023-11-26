package com.dadr.socialnetwork.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterDto(
        @NotBlank(message = "First name cannot be null nor empty")
        String firstName,
        @NotBlank(message = "Last name cannot be null nor empty")
        String lastName,
        @NotBlank(message = "Username cannot be null nor blank, will be used to login")
        String username,
        @NotBlank(message = "Password is mandatory, how are u planning to login?)))")
        @Size(min = 8, message = "Minimum password length is 8")
        String password,
        @Past(message = "Date of birth can only be from past")
        LocalDate dateOfBirth) {
}
