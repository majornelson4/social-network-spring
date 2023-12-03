package com.dadr.socialnetwork.controller;

import com.dadr.socialnetwork.dto.ReadUserDto;
import com.dadr.socialnetwork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/follow")
    public String followUserByUsername(@RequestParam("username") String username) {
        return userService.followUser(username);
    }

    @PostMapping("/unfollow")
    public String unfollowUserByUsername(@RequestParam("username") String username) {
        return userService.unfollowUser(username);
    }

    @GetMapping
    public List<ReadUserDto> findAllUsers() {
        return userService.findAllUsers();
    }

}
