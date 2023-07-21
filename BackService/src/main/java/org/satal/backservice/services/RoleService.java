package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.users.Role;
import org.satal.backservice.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void save(Role role){
        roleRepository.save(role);
    }

    public Optional<Role> findById(Long id){
        return roleRepository.findById(id);
    }

    public Optional<Role> findByTitle(String title){
        return roleRepository.findRoleByTitleRole(title);
    }

    public List<Role> findAll(){
        return (List<Role>) roleRepository.findAll();
    }
}
