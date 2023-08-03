package org.satal.backservice.dto.worcouts;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class CalendarFullDtoForAdmin {
    private String period;
    private Integer currentWeek;
    private Map<String, String> classType;
    private List<WorkoutWeekDto> week;

    public CalendarFullDtoForAdmin(Integer delta, List<String> list) {
        LocalDate cur = LocalDate.now();
        LocalDate newDate = cur.plusDays(delta * 7L);
        LocalDate firstDay = newDate.minusDays(newDate.getDayOfWeek().getValue() - 1);
        LocalDate secondDay = newDate.plusDays(7 - newDate.getDayOfWeek().getValue());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM");
        this.currentWeek = delta;
        this.period = df.format(firstDay) + " - " + df.format(secondDay);
        this.week = List.of(
                new WorkoutWeekDto("Понедельник"),
                new WorkoutWeekDto("Вторник"),
                new WorkoutWeekDto("Среда"),
                new WorkoutWeekDto("Четверг"),
                new WorkoutWeekDto("Пятница"),
                new WorkoutWeekDto("Суббота"),
                new WorkoutWeekDto("Воскресенье")
        );

        this.classType = new TreeMap<>();
        classType.put("All Classes", "");

        for (String s : list) {
            String[] split = s.split(",");
            classType.put(split[6], "." + split[6]);
            WorkoutWeekDto w = week.stream().filter(e -> e.getDayOfWeek().equals(split[0])).findFirst().orElse(null);
            assert w != null;
            w.getDay().add(new WorkoutGridDto(s));
        }


    }
}
