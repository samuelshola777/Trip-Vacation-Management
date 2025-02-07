package com.trip_excursion_management.vacation.service.implementation;

import org.springframework.stereotype.Service;

import com.trip_excursion_management.vacation.dto.request.CreateVacationPlanRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationPlanResponse;
import com.trip_excursion_management.vacation.service.interfaces.VacationPlanService;
import com.trip_excursion_management.vacation.data.model.VacationPlan;
import com.trip_excursion_management.vacation.data.repositories.VacationPlanRepository;
import com.trip_excursion_management.vacation.data.repositories.VacationRepository;
import com.trip_excursion_management.appUser.data.models.Group;
import com.trip_excursion_management.appUser.data.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import com.trip_excursion_management.vacation.data.model.Vacation;
import java.util.List;


@Service
@RequiredArgsConstructor

public class VacationPlanServiceIMPL implements VacationPlanService{

    private final VacationPlanRepository vacationPlanRepository;
    private final GroupRepository groupRepository;
    private final VacationRepository vacationRepository;
    

    @Override
    public CreateVacationPlanResponse createVacationPlan(CreateVacationPlanRequest request) {
      Vacation vacation = vacationRepository.findById(request.getVacationId()).orElseThrow(() -> new RuntimeException("Vacation not found"));
      Group group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("Group not found"));
        VacationPlan vacationPlan = VacationPlan.builder()
       .name(request.getName())
       .description(request.getDescription())
       .startDate(request.getStartDate())
       .endDate(request.getEndDate())
       .vacation(vacation)
       .group(group)
       .build();
       VacationPlan savedVacationPlan = vacationPlanRepository.save(vacationPlan);
       return CreateVacationPlanResponse.builder()
       .message("Vacation plan created successfully")
       .statusCode("201")
       .vacationPlan(savedVacationPlan)
       .build();
    }

    @Override
    public VacationPlan getVacationPlan(Long id) {
        return vacationPlanRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacation plan not found"));
    }


    @Override
    public List<VacationPlan> getAllVacationPlan() {
        return vacationPlanRepository.findAll();
    }

    @Override
    public VacationPlan updateVacationPlan(CreateVacationPlanRequest request) {
        VacationPlan vacationPlan = vacationPlanRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Vacation plan not found"));
        vacationPlan.setName(request.getName());
        vacationPlan.setDescription(request.getDescription());
        vacationPlan.setStartDate(request.getStartDate());
        vacationPlan.setEndDate(request.getEndDate());
        return vacationPlanRepository.save(vacationPlan);
    }

    @Override
    public VacationPlan deleteVacationPlan(Long id) {
        VacationPlan vacationPlan = vacationPlanRepository.findById(id).orElseThrow(() -> new RuntimeException("Vacation plan not found"));
        vacationPlan.setDeleted(true);
        return vacationPlanRepository.save(vacationPlan);
    }



    
}