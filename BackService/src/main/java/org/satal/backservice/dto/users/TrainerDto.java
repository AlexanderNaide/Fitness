package org.satal.backservice.dto.users;

import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;

import java.time.LocalDate;

public class TrainerDto extends UserDto{

    private String login;

    private String middleName;

    private LocalDate birthday;

    private Specialization specialization;


    public TrainerDto(User user) {
        super(user);
        this.login = user.getLogin();
        this.middleName = user.getMiddleName();
        this.birthday = user.getBirthday();
        this.specialization = user.getSpecialization();
    }
}
