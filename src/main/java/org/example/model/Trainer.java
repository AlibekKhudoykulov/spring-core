package org.example.model;

import lombok.*;
import org.example.model.template.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trainer extends BaseEntity {
    private User user;
    private TrainingType trainingType;
}
