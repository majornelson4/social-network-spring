package com.dadr.socialnetwork.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ExceptionDetails {
    LocalDateTime date;
    String message;
    String path;
}
