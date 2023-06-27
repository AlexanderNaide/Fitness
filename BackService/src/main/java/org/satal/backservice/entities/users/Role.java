package org.satal.backservice.entities.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Unsigned
    private Long id;

    @Column(name = "title", nullable = false)
    private String titleRole;

    @OneToMany(mappedBy = "role")
    @JsonBackReference(value = "userList")
    private List<User> userList;

    @Column(name = "spec")
    private boolean containsSpecializations;

}
