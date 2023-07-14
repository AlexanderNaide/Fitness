package org.satal.backservice.dto.worcouts;

import lombok.Data;
import org.satal.backservice.entities.gridClasses.Workout;

import java.util.ArrayList;
import java.util.List;

@Data
public class WorkoutWeekDto {

    private String dayOfWeek;
    private List<WorkoutGridDto> day;

    public WorkoutWeekDto(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        this.day = new ArrayList<>();
    }
}
