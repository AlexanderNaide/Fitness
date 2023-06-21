package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.Maintenance;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.repositories.SpecializationRepository;
import org.satal.backservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialisationService {
    private final SpecializationRepository specializationRepository;

    public void save(Specialization specialization){
        specializationRepository.save(specialization);
    }

    public Optional<Specialization> findById(Long id){
        return specializationRepository.findById(id);
    }

    public List<Specialization> findAll(){
        return (List<Specialization>) specializationRepository.findAll();
    }
}
