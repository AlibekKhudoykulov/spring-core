package org.example;

import org.example.dataInitializer.SampleDataInitializer;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        TraineeService traineeService = context.getBean(TraineeService.class);
        TrainerService trainerService = context.getBean(TrainerService.class);
        TrainingService trainingService = context.getBean(TrainingService.class);

        SampleDataInitializer dataInitializer = new SampleDataInitializer(traineeService, trainerService, trainingService);
        dataInitializer.initializeSampleData();

        System.out.println(traineeService.getTrainee(1).toString());
        System.out.println(traineeService.getTrainee(2).toString());

        System.out.println(trainerService.getTrainer(3).toString());
        System.out.println(trainerService.getTrainer(4).toString());

        System.out.println(trainingService.getTraining(1).toString());
        System.out.println(trainingService.getTraining(2).toString());

        context.close();

    }

}