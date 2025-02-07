package com.trip_excursion_management.vacation.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.vacation.data.model.VacationPlan;



public interface VacationPlanRepository extends JpaRepository<VacationPlan, Long> {
    VacationPlan findByGroup(Long groupId);
    
}
