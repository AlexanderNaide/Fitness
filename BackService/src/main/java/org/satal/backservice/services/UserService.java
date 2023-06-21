package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.Maintenance;
import org.satal.backservice.entities.users.AuthRequest;
import org.satal.backservice.entities.users.Role;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.repositories.RoleRepository;
import org.satal.backservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> findByName(String name){
        return userRepository.findByName(name);
    }

    public Optional<User> getUser(AuthRequest authRequest){
        return userRepository.findUserByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
    }

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }
}
