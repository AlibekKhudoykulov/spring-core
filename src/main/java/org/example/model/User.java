package org.example.model;

import lombok.*;
import org.example.model.template.BaseEntity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;

}
