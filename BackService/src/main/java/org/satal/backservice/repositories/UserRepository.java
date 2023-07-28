package org.satal.backservice.repositories;

import org.satal.backservice.entities.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByName (String name);
    Optional<User> findByLogin (String login);
    Optional<User> findUserByLoginAndPassword (String login, String password);
    Long countAllByIdIsNotNull ();

    @Query(nativeQuery = true,
            value = "SELECT * FROM fitness.users\n" +
                    "where role_id = 4\n" +
                    "and COALESCE (name, '') LIKE :name\n" +
                    "and COALESCE (surname, '') LIKE :surname\n" +
                    "and COALESCE (login, '') LIKE :login\n" +
                    "and COALESCE (phone, '') LIKE :phone\n" +
                    "and COALESCE (email, '') LIKE :email")
    Page<User> findListUsers (@Param("name") String name,
                              @Param("surname") String surname,
                              @Param("login") String login,
                              @Param("phone") String phone,
                              @Param("email") String email,
                              Pageable pageable);
}
