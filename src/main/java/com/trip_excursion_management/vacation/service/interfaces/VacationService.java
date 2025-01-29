package com.trip_excursion_management.vacation.service.interfaces;

import com.trip_excursion_management.vacation.dto.request.CreateVacationRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationResponse;
import java.util.UUID;
import com.trip_excursion_management.vacation.dto.response.GetVacationResponse;
import java.util.List;
import com.trip_excursion_management.vacation.dto.request.AddMediaRequest;
import com.trip_excursion_management.vacation.dto.response.GetVacationDisplayMediaResponse;


public interface VacationService {
    CreateVacationResponse createVacation(CreateVacationRequest request);
    GetVacationResponse getVacation(UUID id);
    List<GetVacationResponse> getAllVacations();
    CreateVacationResponse updateVacation( CreateVacationRequest request);
    CreateVacationResponse deleteVacation(UUID id);
    List<GetVacationResponse> getAllDeletedVacations();
    CreateVacationResponse restoreVacation(UUID id);
    
   
}
