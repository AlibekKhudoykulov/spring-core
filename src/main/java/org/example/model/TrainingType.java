package org.example.model;

import lombok.*;
import org.example.model.template.AbsEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrainingType extends AbsEntity {
    private String name;
}
