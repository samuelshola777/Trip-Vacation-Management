package com.trip_excursion_management.vacation.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.trip_excursion_management.vacation.dto.request.AddMediaRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationResponse;
import com.trip_excursion_management.vacation.dto.response.GetVacationDisplayMediaResponse;

public interface VacationDisplayMediaService {
    CreateVacationResponse uploadVacationDisplayMedia(AddMediaRequest request);
    List<GetVacationDisplayMediaResponse> getAllVacationDisplayMedia(UUID vacationId);
}
