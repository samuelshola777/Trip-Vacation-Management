package com.trip_excursion_management.vacation.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.vacation.data.model.VacationComments;
import java.util.List;
import java.util.UUID;

public interface VacationCommentRepository extends JpaRepository<VacationComments, UUID> {
    List<VacationComments> findAllByVacationId(UUID vacationId);
    
}
