package org.satal.backservice.dto.users;

import jakarta.persistence.Column;
import lombok.Data;
import org.satal.backservice.entities.users.User;

@Data
public class SuperAdminDto implements UserDto {

    private Long id;
    private String type;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String avatar;
    private String background;

    public SuperAdminDto(User user, String type) {
        this.id = user.getId();
        this.type = type;
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.background = user.getBackground();
    }
}
