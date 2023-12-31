package org.satal.backservice.dto.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.satal.backservice.entities.users.Role;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.Ticket;
import org.satal.backservice.entities.users.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserFullDto {

    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String phone;
    private LocalDate birthday;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Role role;
    private Specialization specialization;
    private List<Ticket> ticketList;
    private String avatar;
    private String background;

    public UserFullDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.middleName = user.getMiddleName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.birthday = user.getBirthday();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.role = user.getRole();
        this.specialization = user.getSpecialization();
        this.ticketList = user.getTicketList();
        this.avatar = user.getAvatar();
        this.background = user.getBackground();
    }
}
