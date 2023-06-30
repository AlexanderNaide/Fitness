package org.satal.backservice.repositories;

import org.satal.backservice.entities.users.Specialization;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends CrudRepository<Specialization, Long>, PagingAndSortingRepository<Specialization, Long> {

}
