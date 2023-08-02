package org.satal.backservice.dto.worcouts;

import lombok.Data;
import org.satal.backservice.entities.gridClasses.Workout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class WorkoutGridDto {
    private Long id;
    private String dayOfWeek;
    private String specialization;
    private String trainer;
    private String time;

    public WorkoutGridDto(Workout workout) {
        this.id = workout.getId();
        this.specialization = workout.getSpecialization().getSpecializationTitle();
        this.dayOfWeek = workout.getTime().getDayOfWeek().name();
        this.trainer = workout.getTrainer().getName();
        this.time = new String(workout.getTime().getHour() + ":" + workout.getTime().getMinute());
    }

    public WorkoutGridDto(String str) {
        String[] split = str.split(",");
        DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter resultFormat = DateTimeFormatter.ofPattern("HH:mm");
        this.id = Long.parseLong(split[1]);
        this.dayOfWeek =split[10];
        this.specialization = split[6];
        this.trainer = split[5];
        this.time = resultFormat.format(LocalDateTime.parse(split[9], sourceFormat));

    }
}
