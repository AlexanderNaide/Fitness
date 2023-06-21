package org.satal.backservice.repositories;

import org.satal.backservice.entities.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName (String name);

    Optional<User> findUserByLoginAndPassword (String login, String password);
}
