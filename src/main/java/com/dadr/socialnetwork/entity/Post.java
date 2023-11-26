package com.dadr.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@Entity
@Table(name = "posts")
@ToString(exclude = "comments")
@EqualsAndHashCode(exclude = "comments")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 2000)
    String image;
    @Column(nullable = false, length = 300)
    String content;
    @Column(nullable = false)
    LocalDateTime publishedDate;
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Builder.Default
    Set<Comment> comments = new HashSet<>();

}
