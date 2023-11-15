package org.example.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;

}
