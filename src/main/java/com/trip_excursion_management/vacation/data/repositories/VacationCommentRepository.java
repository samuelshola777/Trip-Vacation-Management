package com.trip_excursion_management.vacation.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.vacation.data.model.VacationComments;
import java.util.List;


public interface VacationCommentRepository extends JpaRepository<VacationComments, Long> {
    List<VacationComments> findAllByVacationId(Long vacationId);
    
}

