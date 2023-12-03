package com.dadr.socialnetwork.dto;

import com.dadr.socialnetwork.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterDto(
        @NotBlank(message = "First name cannot be null nor blank")
        String firstName,
        @NotBlank(message = "Last name cannot be null nor blank")
        String lastName,
        @NotBlank(message = "Username cannot be null nor blank")
        String username,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, max = 300, message = "Minimum password length: 8, max: 300")
        String password,
        @Past(message = "Date of birth can only be from past")
        LocalDate dateOfBirth) {
}
