package com.dadr.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String firstName;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false, length = 300)
    String password;
    @Column(nullable = false, unique = true)
    String username;
    @Column(nullable = false)
    LocalDate dateOfBirth;

    @ManyToMany
    @JoinTable(name = "followers_follows",
            joinColumns = @JoinColumn(name = "user_follower_id"),
            inverseJoinColumns = @JoinColumn(name = "user_follows_id"))
    @Builder.Default
    List<User> followers = new ArrayList<>();

    @ManyToMany(mappedBy = "followers")
    @Builder.Default
    List<User> follows = new ArrayList<>();
}
