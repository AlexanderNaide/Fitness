package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.repositories.SpecializationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    public void save(Specialization specialization){
        specializationRepository.save(specialization);
    }

    public Optional<Specialization> findById(Long id){
        return specializationRepository.findById(id);
    }

    public List<Specialization> findList(){
        return (List<Specialization>) specializationRepository.findAll();
    }

    public Page<Specialization> findAll(Integer page){
        return specializationRepository.findAll(PageRequest.of(page, 15));
    }
}
