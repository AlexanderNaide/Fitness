package org.satal.backservice.dto.users;

import lombok.Data;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;

import java.time.LocalDate;

@Data
public class TrainerDto implements UserDto{

    private Long id;
    private String titleRole;
    private Specialization specialization;
    private String login;
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String phone;
    private LocalDate birthday;

    public TrainerDto(User user) {
        this.id = user.getId();
        this.titleRole = user.getRole().getTitleRole();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.login = user.getLogin();
        this.middleName = user.getMiddleName();
        this.birthday = user.getBirthday();
        this.specialization = user.getSpecialization();
    }
}
