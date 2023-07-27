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

/*    @Modifying
    @Query(nativeQuery = true,
            value =
            "SELECT * FROM fitness.users\n" +
                    "where name like '%User1%'\n" +
                    "and surname like '%1%'\n" +
                    "and login like '%%'\n" +
                    "and phone like '%5%';",
            countQuery = "SELECT count(*) FROM fitness.users\n" +
                    "where name like '%User1%'\n" +
                    "and surname like '%1%'\n" +
                    "and login like '%%'\n" +
                    "and phone like '%5%';")
//    List<User> findListUsers ();
    Page<User> findListUsers (Pageable pageable);*/


    // Вот так огонь
/*    SELECT * FROM fitness.users
    where name like '%User1%'
    and surname like '%1%'
    and login like '%%'
    and COALESCE (phone, '') LIKE '%5%';*/

    @Query(nativeQuery = true,
            value = "SELECT * FROM fitness.users\n" +
                            "where name like '%User1%'\n" +
                            "and surname like '%1%'\n" +
                            "and login like '%%' :param")
    Page<User> findListUsers (@Param("param") String parameters, Pageable pageable);
}
