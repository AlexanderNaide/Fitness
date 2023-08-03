package org.satal.backservice.dto.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.satal.backservice.entities.enums.Gender;
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

    private String gender;
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String phone;
    private String birthday;
    private String createdAt;
//    private String updatedAt;
    private Role role;
    private Specialization specialization;
    private List<Ticket> ticketList;
    private String avatar;
    private String background;

    public UserFullDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.gender = user.getGender().name();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.middleName = user.getMiddleName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        if (user.getBirthday() != null){
            this.birthday = user.getBirthday().getDayOfMonth() + " " + user.getBirthday().getMonth().name() + " " + user.getBirthday().getYear();
//        this.birthday = user.getBirthday();
        }
        if (user.getCreatedAt() != null){
//        this.createdAt = user.getCreatedAt();
            this.createdAt = user.getCreatedAt().getDayOfMonth() + " " + user.getCreatedAt().getMonth().name() + " " + user.getCreatedAt().getYear();
        }
//        if (user.getUpdatedAt() != null){
//        this.updatedAt = user.getUpdatedAt();
//        this.updatedAt = user.getUpdatedAt().getDayOfMonth() + " " + user.getUpdatedAt().getMonth().name() + " " + user.getUpdatedAt().getYear();
//        }
        this.role = user.getRole();
        this.specialization = user.getSpecialization();
        this.ticketList = user.getTicketList();
        if (user.getAvatar() != null){
            this.avatar = user.getAvatar();
        } else {
            if(user.getGender().equals(Gender.MAN)){
                this.avatar = "../../images/plug/m.jpg";
            } else {
                this.avatar = "../../images/plug/f.jpg";
            }
        }
        this.background = user.getBackground();
    }
}
