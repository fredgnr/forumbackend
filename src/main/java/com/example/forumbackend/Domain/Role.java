package com.example.forumbackend.Domain;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = -1L;
    private User user;
    private String token;
}
