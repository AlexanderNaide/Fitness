package org.satal.backservice.entities.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.satal.backservice.entities.enums.Gender;
import org.satal.backservice.entities.gridClasses.Workout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Unsigned
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender")
    private Gender gender;

//    @Column(name = "name", nullable = false)
    @Column(name = "name")
    private String name;

//    @Column(name = "surname", nullable = false)
    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @Unsigned
    private Role role;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    @Unsigned
    private Specialization specialization;

    @OneToMany(mappedBy = "client")
    @JsonBackReference(value = "ticketList") // без этой аннотации jackson тянет все объекты по цепочки, аналог lazyInitialization
    private List<Ticket> ticketList;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "background")
    private String background;

    @OneToMany(mappedBy = "trainer")
    @JsonBackReference(value = "workouts") // без этой аннотации jackson тянет все объекты по цепочки, аналог lazyInitialization
    private List<Workout> workouts;

}
