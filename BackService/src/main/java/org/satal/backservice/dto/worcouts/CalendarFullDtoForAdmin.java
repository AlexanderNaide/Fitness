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
        System.out.println("Началось");
        list.forEach(System.out::println);
        LocalDate cur = LocalDate.now();
        LocalDate newDate = cur.plusDays(delta * 7L);
        LocalDate firstDay = newDate.minusDays(newDate.getDayOfWeek().getValue() - 1);
        LocalDate secondDay = newDate.plusDays(7 - newDate.getDayOfWeek().getValue());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM");
        this.currentWeek = delta;
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

        this.classType = new TreeMap<>();
        classType.put("All Classes", "");

        for (String s : list) {
            String[] split = s.split(",");
            classType.put(split[7], "." + split[7]);
            WorkoutWeekDto w = week.stream().filter(e -> e.getDayOfWeek().equals(split[11])).findFirst().orElse(null);
            assert w != null;
            w.getDay().add(new WorkoutGridDto(s));
        }


    }
}
