package com.trip_excursion_management.vacation.dto.request;

import java.time.LocalDate;
import java.util.UUID;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateVacationPlanRequest {
    private UUID vacationId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID groupId;
    
}