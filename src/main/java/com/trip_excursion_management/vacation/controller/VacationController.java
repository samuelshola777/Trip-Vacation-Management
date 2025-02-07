package com.trip_excursion_management.vacation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trip_excursion_management.vacation.dto.request.CreateVacationPlanRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationPlanResponse;
import com.trip_excursion_management.vacation.service.interfaces.VacationPlanService;
import com.trip_excursion_management.vacation.data.model.VacationPlan;
import lombok.AllArgsConstructor;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/vacation")
public class VacationController {
    private final VacationPlanService vacationPlanService;

    @PostMapping("/create")
    public ResponseEntity<CreateVacationPlanResponse> createVacationPlan(@RequestBody CreateVacationPlanRequest request){
        return ResponseEntity.ok(vacationPlanService.createVacationPlan(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<VacationPlan> getVacationPlan(@PathVariable Long id){
        return ResponseEntity.ok(vacationPlanService.getVacationPlan(id));
    }


    @GetMapping("/get/all")
    public ResponseEntity<List<VacationPlan>> getAllVacationPlan(){
        return ResponseEntity.ok(vacationPlanService.getAllVacationPlan());
    }
}
