package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.gridClasses.Workout;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.repositories.WorkoutRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public void save(Workout workout){
        workoutRepository.save(workout);
    }

    public Optional<Workout> findById(Long id){
        return workoutRepository.findById(id);
    }

    // test
    public List<String> findList(){
        return workoutRepository.getScheduleForWeek(0, "User0_Name");
    }

    public List<String> findCurrentWeek(int delta, String username){
        LocalDateTime currentTime = LocalDateTime.now();
        return workoutRepository.getScheduleForWeek(delta, username);
    }


    /*
    https://www.internet-technologies.ru/articles/rabota-s-datoy-i-vremenem-v-mysql.html

    select concat(datediff() *** dayofmonth(w.time) - dayofweek(w.time), ':', DAYOFWEEK(w.time), '-' , TIMESTAMPDIFF(w.time, 2)) as 'period', w.id, dayname(w.time) as 'day', s.specialization as 'spec', concat(t.name, ' ', t.surname) as 'trainer', concat(hour(w.time), ':', minute(w.time)) as 'time'
from fitness.user_classes as uc
inner join fitness.workouts as w
on uc.class_id = w.id
inner join fitness.users as t
on w.trainer_id = t.id
inner join fitness.specialization as s
on w.specialization_id = s.id
inner join fitness.users as u
on uc.user_id = u.id
where yearweek(w.time) = concat(year(curdate()), week(curdate()) + 0)
and u.login = 'user0'
order by dayofweek(w.time);
*/

}
