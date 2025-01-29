package com.trip_excursion_management.vacation.service.implementation;

import org.springframework.stereotype.Service;

import com.trip_excursion_management.vacation.dto.request.CreateVacationPlanRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationPlanResponse;
import com.trip_excursion_management.vacation.service.interfaces.VacationPlanService;
import com.trip_excursion_management.vacation.data.model.VacationPlan;
import com.trip_excursion_management.vacation.data.repositories.VacationPlanRepository;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor

public class VacationPlanServiceIMPL implements VacationPlanService{

    private final VacationPlanRepository vacationPlanRepository;
    private final 
    

    @Override
    public CreateVacationPlanResponse createVacationPlan(CreateVacationPlanRequest request) {
       
    }
}