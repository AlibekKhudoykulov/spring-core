package org.example.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trainee {
    private int id;
    private User user;
    private Date dateOfBirth;
    private String address;


}
