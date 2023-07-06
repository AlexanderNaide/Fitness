package org.satal.backservice.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.users.Role;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        User user = userService.findByLogin(login).orElseThrow(() -> new BadCredentialsException(String.format("Пользователь '%s' отсутствует в базе данных", login)));
//        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles())); // когда пользователи содержат роли в списках
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().getTitleRole())));
    }

    // Конструктор для списка ролей
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
//    }

//    public Integer containsUsername(String username){
//        return userRepository.countByUsername(username);
//    }
}
