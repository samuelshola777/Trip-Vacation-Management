package com.trip_excursion_management.vacation.dto.response;


import lombok.Builder;
import lombok.Data;
@Builder
@Data

public class CreateVacationResponse {
    private String message;
    private String status;
    private String code;
    private Long id;
}
