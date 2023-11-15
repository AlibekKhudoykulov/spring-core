package org.example.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trainer {
    private int id;
    private User user;
    private TrainingType trainingType;
}
