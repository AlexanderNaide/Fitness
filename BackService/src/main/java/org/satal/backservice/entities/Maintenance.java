package org.satal.backservice.entities;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Data;

@Data
@Entity
@Table(name = "maintenance")
public class Maintenance {

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

}
