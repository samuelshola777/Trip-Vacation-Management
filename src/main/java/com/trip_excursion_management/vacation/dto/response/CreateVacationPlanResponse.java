package com.trip_excursion_management.vacation.dto.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CreateVacationPlanResponse {
    private String message;
    private String status;
    private String code;
    private UUID vacationId;
}