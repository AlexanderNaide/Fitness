package org.satal.backservice.entities.gridClasses;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "workouts")
@NoArgsConstructor
@AllArgsConstructor
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Unsigned
    private Long id;

    @Column(name = "is_group")
    private boolean isGroup;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @Unsigned
    private User trainer;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    @Unsigned
    private Specialization specialization;

    @ManyToMany
    @JoinTable(name = "user_classes",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> customers;

    @Column(name = "time")
    private LocalDateTime time;



}
