package org.example.model;

import lombok.*;
import org.example.model.template.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrainingType extends BaseEntity {
    private String name;
}
