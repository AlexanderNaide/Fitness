package org.satal.backservice.repositories;

import org.satal.backservice.entities.users.SeasonTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonTicketRepository extends CrudRepository<SeasonTicket, Long> {

}
