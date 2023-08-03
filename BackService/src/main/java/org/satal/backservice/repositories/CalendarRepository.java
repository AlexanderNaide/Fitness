package org.satal.backservice.repositories;

import org.satal.backservice.dto.worcouts.ClassFullDto;
import org.satal.backservice.entities.gridClasses.CalendarEvent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends CrudRepository<CalendarEvent, Long> {

/*    @Query(nativeQuery = true, value =
            "SELECT w.title as day, c.id, c.title, c.start, c.end, concat(tr.name, ' ', tr.surname) as trainer, s.specialization, c.duration, c.replay, (timestampadd(day, c.replay * (DATEDIFF(DATE_ADD(curdate(), INTERVAL (w.id - dayofweek(curdate() - 1) + 1 + (7 * 0)) DAY), c.start) / c.replay), c.start)) as 'event_date'\n" +
                    "                    FROM fitness.week as w,\n" +
                    "                    fitness.calendar as c\n" +
                    "                    inner join fitness.users as tr\n" +
                    "                    on c.trainer_id = tr.id\n" +
                    "                    inner join fitness.specialization as s\n" +
                    "                    on c.discipline_id = s.id\n" +
                    "                    where DATE_ADD(curdate(), INTERVAL (w.id - dayofweek(curdate() - 1) + 1 + (7 * :delta)) DAY) between c.start and c.end\n" +
                    "                    and DATEDIFF(DATE_ADD(curdate(), INTERVAL (w.id - dayofweek(curdate() - 1) + 1 + (7 * :delta)) DAY), c.start) % c.replay = 0\n" +
                    "                    order by event_date;")
    List<String> getCalendarForWeek(@Param("delta") Integer delta);*/


    @Query(nativeQuery = true, value =
            "SELECT w.title as day, c.id, c.title, c.start, c.end, concat(tr.name, ' ', tr.surname) as trainer, s.specialization, c.duration, c.replay, (timestampadd(day, c.replay * (DATEDIFF(DATE_ADD(curdate(), INTERVAL (w.id - dayofweek(curdate() - 1) + 1 + (7 * :delta)) DAY), c.start) / c.replay), c.start)) as 'event_date' FROM fitness.week as w, fitness.calendar as c inner join fitness.users as tr on c.trainer_id = tr.id inner join fitness.specialization as s on c.discipline_id = s.id where DATE_ADD(curdate(), INTERVAL (w.id - dayofweek(curdate() - 1) + 1 + (7 * :delta)) DAY) between c.start and c.end and DATEDIFF(DATE_ADD(curdate(), INTERVAL (w.id - dayofweek(curdate() - 1) + 1 + (7 * :delta)) DAY), c.start) % c.replay = 0 order by event_date;")
    List<String> getCalendarForWeek(@Param("delta") Integer delta);

    // Предполагается, что интервалы событий заданы в днях
    @Query(nativeQuery = true, value =
            "SELECT f.id, f.title, f.start, f.end, f.duration, f.replay, s.specialization, concat(u.name, ' ', u.surname) as trainer FROM fitness.calendar as f inner join fitness.specialization as s on f.discipline_id = s.id inner join fitness.users as u on f.trainer_id = u.id where f.id = :id and datediff(timestamp(:dateTime), f.start) % f.replay = 0;")
    List<String> getClass(@Param("id") Long id, @Param("dateTime") String dateTime);


    // Тот же запрос, но с калибровкой по часам
    /*SELECT * FROM fitness.calendar as f
    where id = :id
    and timediff(f.start, timestamp(:dateTime)) % (f.replay * 24) = 0;*/

/*    @Modifying
    @Query(nativeQuery = true, value =
            "SELECT * FROM fitness.calendar;")
    List<String> getCalendarForWeek(@Param("delta") Integer delta);*/
}
