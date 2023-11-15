package org.example.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Training {
    private int id;
    private Trainee trainee;
    private Trainer trainer;
    private String trainingName;
    private TrainingType trainingType;
    private Date trainingDate;
    private float trainingDuration;
}
