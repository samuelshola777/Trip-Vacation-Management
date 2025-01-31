package com.trip_excursion_management.vacation.dto.response;

import java.util.UUID;

import com.trip_excursion_management.vacation.data.model.VacationPlan;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CreateVacationPlanResponse {
    private String message;
    private String statusCode;
    private String code;
    private UUID vacationId;
    private VacationPlan vacationPlan;
}