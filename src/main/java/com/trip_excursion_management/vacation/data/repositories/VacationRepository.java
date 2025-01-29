package com.trip_excursion_management.vacation.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.vacation.data.model.Vacation;
import com.trip_excursion_management.vacation.data.model.VacationTripTypes;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
public interface VacationRepository extends JpaRepository<Vacation, UUID> {
    boolean existsByTripType(VacationTripTypes tripType);
    Optional<Vacation> findByTripType(VacationTripTypes tripType);
    List<Vacation> findAllByIsDeletedTrue();

    
}
