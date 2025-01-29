package com.trip_excursion_management.vacation.dto.response;

import com.trip_excursion_management.vacation.data.model.Vacation;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class GetVacationResponse {
    private String message;
    private String status;
    private String code;
    private Vacation data;
}
