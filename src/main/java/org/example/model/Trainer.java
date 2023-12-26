package org.example.model;

import lombok.*;
import org.example.model.template.AbsEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trainer extends AbsEntity {
    private User user;
    private TrainingType trainingType;
}
