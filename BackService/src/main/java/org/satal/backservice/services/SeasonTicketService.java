package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.users.Role;
import org.satal.backservice.entities.users.SeasonTicket;
import org.satal.backservice.repositories.RoleRepository;
import org.satal.backservice.repositories.SeasonTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeasonTicketService {
    private final SeasonTicketRepository seasonTicketRepository;

    public void save(SeasonTicket seasonTicket){
        seasonTicketRepository.save(seasonTicket);
    }

    public Optional<SeasonTicket> findById(Long id){
        return seasonTicketRepository.findById(id);
    }

    public List<SeasonTicket> findAll(){
        return (List<SeasonTicket>) seasonTicketRepository.findAll();
    }
}
