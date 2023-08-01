package org.satal.backservice.repositories;

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
            "SELECT *, dayname(event_date) from (\n" +
                    "                    SELECT w.title as day, c.id, c.title, c.start, c.end, concat(tr.name, ' ', tr.surname) as trainer, s.specialization, c.duration, c.replay, (timestampadd(day, c.replay * (DATEDIFF(DATE_ADD(curdate(), INTERVAL (w.id - dayofweek(curdate() - 1) + 1 + (7 * 0)) DAY), c.start) / c.replay), c.start)) as 'event_date'\n" +
                    "                    FROM fitness.week as w,\n" +
                    "                    fitness.calendar as c\n" +
                    "                    inner join fitness.users as tr\n" +
                    "                    on c.trainer_id = tr.id\n" +
                    "                    inner join fitness.specialization as s\n" +
                    "                    on c.discipline_id = s.id\n" +
                    "                    where DATEDIFF(DATE_ADD(curdate(), INTERVAL (w.id - dayofweek(curdate() - 1) + 1 + (7 * 0)) DAY), c.start) % c.replay = 0\n" +
                    "            ) as t where event_date between start and end\n" +
                    "            order by event_date;")
    List<String> getCalendarForWeek(@Param("delta") Integer delta);*/


    @Modifying
    @Query(nativeQuery = true, value =
            "SELECT *, dayname(event_date) from (SELECT w.title as day, c.id, c.title, c.start, c.end, concat(tr.name, ' ', tr.surname) as trainer, s.specialization, c.duration, c.replay, (timestampadd(day, c.replay * (DATEDIFF(DATE_ADD(curdate(), INTERVAL (w.id - dayofweek(curdate() - 1) + 1 + (7 * 0)) DAY), c.start) / c.replay), c.start)) as 'event_date' FROM fitness.week as w, fitness.calendar as c inner join fitness.users as tr on c.trainer_id = tr.id inner join fitness.specialization as s on c.discipline_id = s.id where DATEDIFF(DATE_ADD(curdate(), INTERVAL (w.id - dayofweek(curdate() - 1) + 1 + (7 * :delta)) DAY), c.start) % c.replay = 0) as t where event_date between start and end order by event_date;")
    List<String> getCalendarForWeek(@Param("delta") Integer delta);

/*    @Modifying
    @Query(nativeQuery = true, value =
            "SELECT * FROM fitness.calendar;")
    List<String> getCalendarForWeek(@Param("delta") Integer delta);*/
}
