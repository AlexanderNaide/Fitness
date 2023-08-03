package org.satal.backservice.dto.users;

import lombok.Data;
import org.satal.backservice.entities.users.Ticket;
import org.satal.backservice.entities.users.User;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClientDto implements UserDto {

    private Long id;
    private String titleRole;
    private String login;
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String phone;
    private LocalDate birthday;
    private List<Ticket> ticketList;

//    private String createdAt;


    public ClientDto(User user) {
        this.id = user.getId();
        this.titleRole = user.getRole().getTitleRole();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.login = user.getLogin();
        this.middleName = user.getMiddleName();
        this.birthday = user.getBirthday();
        this.ticketList = user.getTicketList();
//        this.createdAt = user.getCreatedAt().getDayOfMonth() + " " + user.getCreatedAt().getMonth().name() + " " + user.getCreatedAt().getYear();
    }
}
