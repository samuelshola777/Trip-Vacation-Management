package com.trip_excursion_management.vacation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.trip_excursion_management.vacation.data.model.VacationPlan;
import com.trip_excursion_management.vacation.dto.request.CreateVacationPlanRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationPlanResponse;
import com.trip_excursion_management.vacation.service.interfaces.VacationPlanService;
import org.springframework.web.bind.annotation.PutMapping;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vacation/plan")
public class VacationPlanController {
    private final VacationPlanService vacationPlanService;

    @PostMapping("/create")
    public ResponseEntity<CreateVacationPlanResponse> createVacationPlan(@RequestBody CreateVacationPlanRequest request){
        return ResponseEntity.ok(vacationPlanService.createVacationPlan(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<VacationPlan> getVacationPlan(@PathVariable UUID id){
        return ResponseEntity.ok(vacationPlanService.getVacationPlan(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<VacationPlan>> getAllVacationPlan(){
        return ResponseEntity.ok(vacationPlanService.getAllVacationPlan());
    }

    @PutMapping("/update")
    public ResponseEntity<VacationPlan> updateVacationPlan(@RequestBody CreateVacationPlanRequest request){
        return ResponseEntity.ok(vacationPlanService.updateVacationPlan(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VacationPlan> deleteVacationPlan(@PathVariable UUID id){
        return ResponseEntity.ok(vacationPlanService.deleteVacationPlan(id));
    }
}
