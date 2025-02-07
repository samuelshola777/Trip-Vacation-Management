package com.trip_excursion_management.vacation.service.interfaces;
import java.util.List;

import com.trip_excursion_management.vacation.data.model.VacationPlan;
import com.trip_excursion_management.vacation.dto.request.CreateVacationPlanRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationPlanResponse;

public interface VacationPlanService {
    CreateVacationPlanResponse createVacationPlan(CreateVacationPlanRequest request);
    VacationPlan getVacationPlan(Long id);
    List<VacationPlan> getAllVacationPlan();
    VacationPlan updateVacationPlan(CreateVacationPlanRequest request);
    VacationPlan deleteVacationPlan(Long id);
}
