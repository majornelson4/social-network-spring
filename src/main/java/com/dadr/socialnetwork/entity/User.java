package com.dadr.socialnetwork.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@ToString(exclude = {"posts", "followers", "follows"})
@EqualsAndHashCode(exclude = {"posts", "followers", "follows"})
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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "followers_follows",
            joinColumns = @JoinColumn(name = "user_follower_id"),
            inverseJoinColumns = @JoinColumn(name = "user_follows_id"))
    @Builder.Default
    List<User> followers = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "followers", fetch = FetchType.EAGER)
    @Builder.Default
    List<User> follows = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    @Builder.Default
    List<Post> posts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    Role role;
}
