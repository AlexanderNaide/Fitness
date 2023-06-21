package org.satal.backservice.dto.users;

import org.satal.backservice.entities.users.User;
import java.time.LocalDate;

public class AdminDto extends UserDto{

    private String login;

    private String middleName;

    private LocalDate birthday;

    public AdminDto(User user) {
        super(user);
        this.login = user.getLogin();
        this.middleName = user.getMiddleName();
        this.birthday = user.getBirthday();
    }
}
