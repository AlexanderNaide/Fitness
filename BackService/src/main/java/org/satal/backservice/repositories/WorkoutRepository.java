package org.satal.backservice.repositories;

import org.satal.backservice.entities.gridClasses.Workout;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long> {

    @Modifying
    @Query(nativeQuery = true, value =
            "select w.id, dayname(w.time) as 'day', s.specialization as 'spec', concat(t.name, ' ', t.surname) as 'trainer', concat(hour(w.time), ':', minute(w.time)) as 'time'\n" +
                    "from fitness.user_classes as uc\n" +
                    "inner join fitness.workouts as w\n" +
                    "on uc.class_id = w.id\n" +
                    "inner join fitness.users as t\n" +
                    "on w.trainer_id = t.id\n" +
                    "inner join fitness.specialization as s\n" +
                    "on w.specialization_id = s.id\n" +
                    "inner join fitness.users as u\n" +
                    "on uc.user_id = u.id\n" +
                    "where yearweek(w.time) = concat(year(curdate()), week(curdate(), 1) + :delta)\n" +
                    "and u.login = :username\n" +
                    "order by dayofweek(w.time);")
    List<String> getScheduleForWeek(@Param("delta") Integer delta, @Param("username") String username);

}
