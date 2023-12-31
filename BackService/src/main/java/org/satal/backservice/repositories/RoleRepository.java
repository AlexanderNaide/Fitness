package org.satal.backservice.repositories;

import org.satal.backservice.entities.users.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findRoleByTitleRole (String role);

}
