package org.satal.backservice.api;

import lombok.Data;
import lombok.Value;
import org.satal.backservice.entities.users.User;

import java.util.Optional;

//@Value
@Data
public class AuthResponse {
    String token;
    String username;
    String surname;

    public AuthResponse(String token, User user) {
        this.token = token;
        if (user != null){
            this.username = user.getName();
            this.surname = user.getSurname();
        }
        System.out.println(username);
        System.out.println(surname);
    }
}
