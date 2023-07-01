package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.api.AuthRequest;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.repositories.UserRepository;
import org.satal.backservice.repositories.UserSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    private final SpecializationService specializationService;

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
//    public List<User> findAll(){
//        return (List<User>) userRepository.findAll();
//    }

    public Page<User> findAll(Long role, Long specialization, String value, Integer page){ // вот тут попробовать переделать на LOnd Id, по каждой сущности

//        System.out.println("Service >>>");
        Specification<User> spec = Specification.where(null);

        if(role != null){
//            Optional<Role> currentRole = roleService.findById(role);
//            if(currentRole.isPresent()){
//                spec = spec.and(UserSpecifications.equalRole(currentRole.get()));
//            }
            spec = spec.and(UserSpecifications.equalRoleById(role));
        }
        if(specialization != null){
//            Optional<Specialization> currentSpecialization = specialisationService.findById(specialization);
//            if(currentSpecialization.isPresent()){
//                spec = spec.and(UserSpecifications.equalSpecialization(currentSpecialization.get()));
//            }
            spec = spec.and(UserSpecifications.equalSpecializationById(specialization));
        }

        if(value != null){
            spec = spec.and(UserSpecifications.nameLike(value)).or(UserSpecifications.surnameLike(value));
        }

        return userRepository.findAll(spec, PageRequest.of(page - 1, 15));
    }
}
