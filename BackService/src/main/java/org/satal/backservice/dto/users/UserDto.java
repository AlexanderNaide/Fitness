package org.satal.backservice.dto.users;

import org.satal.backservice.entities.users.Role;
import org.satal.backservice.entities.users.User;

import java.util.Collection;

public abstract class UserDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private Collection<Role> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.roles = user.getRoles();
    }
}
