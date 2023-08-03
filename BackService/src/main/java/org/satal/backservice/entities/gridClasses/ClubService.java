package org.satal.backservice.entities.gridClasses;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Data;
import org.satal.backservice.entities.users.Specialization;

import java.util.List;

@Data
@Entity
@Table(name = "club_service")
public class ClubService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Unsigned
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 1200)
    private String description;

    @Column(name = "ico", nullable = false)
    private String ico;

    @OneToMany(mappedBy = "clubService")
    @JsonBackReference(value = "specializations") // без этой аннотации jackson тянет все объекты по цепочки, аналог lazyInitialization
    private List<Specialization> specializations;

}
