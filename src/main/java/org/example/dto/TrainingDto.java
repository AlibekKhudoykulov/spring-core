package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Trainee;
import org.example.model.Trainer;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingDto {
        private int id;
        private int traineeId;
        private int trainerId;
        private String trainingName;
        private int trainingTypeId;
        private Date trainingDate;
        private float trainingDuration;
}
