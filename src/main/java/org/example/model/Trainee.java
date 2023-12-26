package org.example.model;

import lombok.*;
import org.example.model.template.AbsEntity;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trainee extends AbsEntity {
    private User user;
    private Date dateOfBirth;
    private String address;
}
