package org.satal.backservice.dto.worcouts;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class WorkoutFullDto {
    private String period;
    private List<WorkoutWeekDto> week;

    public WorkoutFullDto(Integer delta, List<String> list) {
        LocalDate cur = LocalDate.now();
        LocalDate newDate = cur.plusDays(delta * 7L);
        LocalDate firstDay = newDate.minusDays(newDate.getDayOfWeek().getValue() - 1);
        LocalDate secondDay = newDate.plusDays(7 - newDate.getDayOfWeek().getValue());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM");
        this.period = df.format(firstDay) + " - " + df.format(secondDay);
        this.week = List.of(
                new WorkoutWeekDto("Monday"),
                new WorkoutWeekDto("Tuesday"),
                new WorkoutWeekDto("Wednesday"),
                new WorkoutWeekDto("Thursday"),
                new WorkoutWeekDto("Friday"),
                new WorkoutWeekDto("Saturday"),
                new WorkoutWeekDto("Sunday")
        );

        for (String s : list) {
            String[] split = s.split(",");
            WorkoutWeekDto w = week.stream().filter(e -> e.getDayOfWeek().equals(split[1])).findFirst().orElse(null);
            assert w != null;
            w.getDay().add(new WorkoutGridDto(s));
        }
    }
}
