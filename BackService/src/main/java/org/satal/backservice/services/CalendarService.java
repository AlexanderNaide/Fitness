package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.gridClasses.CalendarEvent;
import org.satal.backservice.entities.gridClasses.Workout;
import org.satal.backservice.repositories.CalendarRepository;
import org.satal.backservice.repositories.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public void save(CalendarEvent calendarEvent){
        calendarRepository.save(calendarEvent);
    }

    public Optional<CalendarEvent> findById(Long id){
        return calendarRepository.findById(id);
    }

    // test
    public List<String> findList(){
        return calendarRepository.getCalendarForWeek(0);
    }

    public List<String> getCalendarForWeek(int delta){
        return calendarRepository.getCalendarForWeek(delta);
    }



    // https://www.internet-technologies.ru/articles/rabota-s-datoy-i-vremenem-v-mysql.html

}
