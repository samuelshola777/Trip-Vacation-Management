package com.trip_excursion_management.vacation.service.implementation;

import com.trip_excursion_management.vacation.service.interfaces.VacationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.trip_excursion_management.vacation.dto.request.CreateVacationRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationResponse;
import com.trip_excursion_management.vacation.dto.response.GetVacationResponse;
import java.util.List;
import java.util.ArrayList;

import com.trip_excursion_management.vacation.data.model.Vacation;
import com.trip_excursion_management.vacation.data.repositories.VacationRepository;



@Service
@Transactional
@RequiredArgsConstructor
public class VacationServiceIMPL implements VacationService {
    private final VacationRepository vacationRepository;
  
  


    @Override
    public CreateVacationResponse createVacation(CreateVacationRequest request) {
        Vacation foundVacation = vacationRepository.findByTripType(request.getTripType()).orElse(null);

        if(foundVacation != null && foundVacation.isDeleted() == false){
            throw new RuntimeException("Vacation already exists");
        }
        if(foundVacation != null && foundVacation.isDeleted() == true){
            foundVacation.setDeleted(false);
            foundVacation.setName(request.getName());
            foundVacation.setDescription(request.getDescription());
            foundVacation.setTripType(request.getTripType());
            vacationRepository.save(foundVacation);
            return CreateVacationResponse.builder()
            .message("Vacation updated successfully")
            .status("success")
            .code("200")
            .id(foundVacation.getId())
            .build();
        }
        Vacation vacation = Vacation.builder()
        .name(request.getName())
        .description(request.getDescription())
        .isDeleted(false)
        .tripType(request.getTripType())
        .build();
        vacationRepository.save(vacation);
return CreateVacationResponse.builder()
.message("Vacation created successfully")
.status("success")
.code("200")
.id(vacation.getId())
.build();

}

@Override
public GetVacationResponse getVacation(Long id) {
    Vacation vacation = vacationRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacation not found"));
    return GetVacationResponse.builder()
    .message("Vacation retrieved successfully")
    .status("success")
    .code("200")
    .data(vacation)

    .build();
}

@Override
public List<GetVacationResponse> getAllVacations() {
    List<Vacation> vacations = vacationRepository.findAllByIsDeletedTrue();
    List<GetVacationResponse> responses = new ArrayList<>();
    for(Vacation vacation : vacations){
        responses.add(GetVacationResponse.builder()
        .message("Vacation retrieved successfully")
        .status("success")
        .code("200")
        .data(vacation)
        .build());
    }
    return responses;
}

@Override
public CreateVacationResponse updateVacation(CreateVacationRequest request) {
    Vacation vacation = vacationRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Vacation not found"));
    
    if (request.getName() != null && !request.getName().isEmpty()) {
        vacation.setName(request.getName());
    }
    if (request.getDescription() != null && !request.getDescription().isEmpty()) {
        vacation.setDescription(request.getDescription());
    }
    if (request.getTripType() != null) {
        vacation.setTripType(request.getTripType());
    }
    
    vacationRepository.save(vacation);
    return CreateVacationResponse.builder()
    .message("Vacation updated successfully")
    .status("success")
    .code("200")
    .id(vacation.getId())
    .build();
}





@Override
public CreateVacationResponse deleteVacation(Long id) {
    Vacation vacation = vacationRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacation not found"));
    vacation.setDeleted(true);

    vacationRepository.save(vacation);
    return CreateVacationResponse.builder()
    .message("Vacation deleted successfully")
    .status("success")
    .code("200")
    .id(vacation.getId())
    .build();
}


@Override
public List<GetVacationResponse> getAllDeletedVacations() {
    List<Vacation> vacations = vacationRepository.findAllByIsDeletedTrue();
    List<GetVacationResponse> responses = new ArrayList<>();
    for(Vacation vacation : vacations){
        responses.add(GetVacationResponse.builder()
        .message("Vacation retrieved successfully")
        .status("success")
        .code("200")
        .data(vacation)
        .build());
    }
    return responses;
}

@Override
public CreateVacationResponse restoreVacation(Long id) {
    Vacation vacation = vacationRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacation not found"));
    vacation.setDeleted(false);
    vacationRepository.save(vacation);
    return CreateVacationResponse.builder()
    .message("Vacation restored successfully")

    .status("success")
    .code("200")
    .id(vacation.getId())
    .build();
}



}