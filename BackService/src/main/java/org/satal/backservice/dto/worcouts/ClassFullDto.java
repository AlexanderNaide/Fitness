package org.satal.backservice.dto.worcouts;

import lombok.Data;
import org.satal.backservice.entities.gridClasses.Workout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ClassFullDto {
    private Long id;
    private String title;
    private String startEvent;
    private String endEvent;
    private Integer duration;
    private Integer replay;
    private String specialization;
    private String trainer;
//    private String time;
    private String dateTime;

    public ClassFullDto(String str, String dateTime) {
        System.out.println(str);
        String[] split = str.split(",");
        DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
//        DateTimeFormatter resultFormat = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter resultDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.id = Long.parseLong(split[0]);
        this.title = split[1];
        this.startEvent = resultDateTimeFormat.format(LocalDateTime.parse(split[2], sourceFormat));
        this.endEvent = resultDateTimeFormat.format(LocalDateTime.parse(split[3], sourceFormat));
        this.duration = Integer.parseInt(split[4]);
        this.replay = Integer.parseInt(split[5]);
        this.specialization = split[6];
        this.trainer = split[7];
        this.dateTime = dateTime;

    }
}
