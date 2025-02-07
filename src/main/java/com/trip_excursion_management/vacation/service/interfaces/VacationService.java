package com.trip_excursion_management.vacation.service.interfaces;

import com.trip_excursion_management.vacation.dto.request.CreateVacationRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationResponse;
import com.trip_excursion_management.vacation.dto.response.GetVacationResponse;
import java.util.List;



public interface VacationService {
    CreateVacationResponse createVacation(CreateVacationRequest request);
    GetVacationResponse getVacation(Long id);
    List<GetVacationResponse> getAllVacations();
    CreateVacationResponse updateVacation( CreateVacationRequest request);
    CreateVacationResponse deleteVacation(Long id);
    List<GetVacationResponse> getAllDeletedVacations();
    CreateVacationResponse restoreVacation(Long id);
    

   
}
