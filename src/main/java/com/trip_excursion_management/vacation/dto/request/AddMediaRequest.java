package com.trip_excursion_management.vacation.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddMediaRequest {
    private MultipartFile media;
    private Long vacationId;
    private String description;
}

