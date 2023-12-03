package com.dadr.socialnetwork.service;

import com.dadr.socialnetwork.dto.ReadUserDto;
import com.dadr.socialnetwork.entity.User;
import com.dadr.socialnetwork.exception.ApplicationApiException;
import com.dadr.socialnetwork.exception.ResourceNotFoundException;
import com.dadr.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dadr.socialnetwork.mapper.UserMapper.MAPPER;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public String followUser(String usernameToFollow) {
        User toFollow = validateUser(usernameToFollow);
        User origin = validateUser(getUsernameOfAuthenticatedUser());

        if(origin.getUsername().equals(toFollow.getUsername())) throw new ApplicationApiException("You cannot follow yourself");
        if(origin.getFollows().contains(toFollow) && toFollow.getFollowers().contains(origin)) throw new ApplicationApiException("You already follow this user");

        origin.getFollows().add(toFollow);
        toFollow.getFollowers().add(origin);

        userRepository.save(origin);
        userRepository.save(toFollow);

        return "You successfully followed user.";
    }

    public List<ReadUserDto> findAllUsers() {
        return userRepository.findAll().stream().map(MAPPER::mapToDto).collect(Collectors.toList());
    }

    public String unfollowUser(String usernameToUnfollow) {
        User toUnfollow = validateUser(usernameToUnfollow);
        User loggedInUser = validateUser(getUsernameOfAuthenticatedUser());

        if(loggedInUser.getUsername().equals(toUnfollow.getUsername())) throw new ApplicationApiException("You cannot unfollow yourself");
        if(!(loggedInUser.getFollows().contains(toUnfollow) && toUnfollow.getFollowers().contains(loggedInUser))) throw new ApplicationApiException("You already has unfollowed this user");

        loggedInUser.getFollows().remove(toUnfollow);
        toUnfollow.getFollowers().remove(loggedInUser);

        userRepository.save(loggedInUser);
        userRepository.save(toUnfollow);

        return "You successfully unfollowed user.";
    }

    private User validateUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Invalid username"));
    }

    private String getUsernameOfAuthenticatedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(authentication -> (UsernamePasswordAuthenticationToken)authentication)
                .map(authenticatedUser -> (UserDetails)authenticatedUser.getPrincipal())
                .map(UserDetails::getUsername)
                .orElseThrow(() -> new ApplicationApiException("No one is authenticated"));
    }

}
