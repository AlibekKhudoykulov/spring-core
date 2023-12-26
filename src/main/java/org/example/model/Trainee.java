package org.example.model;

import lombok.*;
import org.example.model.template.BaseEntity;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trainee extends BaseEntity {
    private User user;
    private Date dateOfBirth;
    private String address;
}
