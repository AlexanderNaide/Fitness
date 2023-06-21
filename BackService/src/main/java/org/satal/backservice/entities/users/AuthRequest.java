package org.satal.backservice.entities.users;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String login;
    private String password;

}
