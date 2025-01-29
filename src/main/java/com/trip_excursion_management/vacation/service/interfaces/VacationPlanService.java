package com.trip_excursion_management.vacation.service.interfaces;

import com.trip_excursion_management.vacation.dto.request.CreateVacationPlanRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationPlanResponse;

public interface VacationPlanService {
    CreateVacationPlanResponse createVacationPlan(CreateVacationPlanRequest request);
}
