package com.trip_excursion_management.vacation.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.vacation.data.model.VacationHistory;

import java.util.UUID;

public interface VacationHistoryRepository extends JpaRepository<VacationHistory, UUID> {
    
}
