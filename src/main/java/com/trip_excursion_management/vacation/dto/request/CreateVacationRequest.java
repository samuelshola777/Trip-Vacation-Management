package com.trip_excursion_management.vacation.dto.request;

import lombok.Data;
import lombok.Builder;

import com.trip_excursion_management.vacation.data.model.VacationTripTypes;

@Data
@Builder
public class CreateVacationRequest {
    private String name;
    private String description;
    private VacationTripTypes tripType;
    private Long id;
}

