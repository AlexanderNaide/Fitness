package org.satal.backservice.entities.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.satal.backservice.entities.gridClasses.Workout;
import org.satal.backservice.entities.gridClasses.ClubService;

import java.util.List;

@Entity
@Data
@Table(name = "specialization")
@NoArgsConstructor
@AllArgsConstructor
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Unsigned
    private Long id;

    @Column(name = "specialization", nullable = false)
    private String specializationTitle;

    @OneToMany(mappedBy = "specialization")
    @JsonBackReference(value = "specializationList")
    private List<User> specializationList;

    @ManyToOne
    @JoinColumn(name = "club_service_id")
    @Unsigned
    private ClubService clubService;

    @OneToMany(mappedBy = "specialization")
    @JsonBackReference(value = "workouts")
    private List<Workout> workouts;

}
