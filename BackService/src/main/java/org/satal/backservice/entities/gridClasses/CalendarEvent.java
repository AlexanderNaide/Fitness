package org.satal.backservice.entities.gridClasses;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "calendar")
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Unsigned
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "end")
    private LocalDateTime end;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "replay")
    private Integer replay;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    @Unsigned
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @Unsigned
    private User trainer;

}
