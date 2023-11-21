package com.dadr.socialnetwork.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ExceptionDetails {
    LocalDateTime date;
    String message;
    String description;
}
