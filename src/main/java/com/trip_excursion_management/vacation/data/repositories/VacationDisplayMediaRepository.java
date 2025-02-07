package com.trip_excursion_management.vacation.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.vacation.data.model.VacationDisplayMedia;

import java.util.List;


public interface VacationDisplayMediaRepository extends JpaRepository<VacationDisplayMedia, Long> {
    

    

    List<VacationDisplayMedia> findAllByVacationId(Long vacationId);

}
