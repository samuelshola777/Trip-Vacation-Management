package com.trip_excursion_management.vacation.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.vacation.data.model.VacationPlan;

import java.util.UUID;

public interface VacationPlanRepository extends JpaRepository<VacationPlan, UUID> {
    VacationPlan findByGroup(UUID groupId);
    
}
