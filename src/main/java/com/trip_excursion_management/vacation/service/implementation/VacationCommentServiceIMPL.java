package com.trip_excursion_management.vacation.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.trip_excursion_management.appUser.data.models.AppUser;
import com.trip_excursion_management.appUser.data.repository.AppUserRepository;
import com.trip_excursion_management.appUser.dtos.response.AppUserResponse;
import com.trip_excursion_management.vacation.data.model.Vacation;
import com.trip_excursion_management.vacation.data.model.VacationComments;
import com.trip_excursion_management.vacation.data.repositories.VacationCommentRepository;
import com.trip_excursion_management.vacation.data.repositories.VacationRepository;
import com.trip_excursion_management.vacation.dto.request.MakeCommentForVacationRequest;
import com.trip_excursion_management.vacation.dto.response.GetVacationCommentResponse;
import com.trip_excursion_management.vacation.service.interfaces.VacationCommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VacationCommentServiceIMPL implements VacationCommentService{

  private final VacationCommentRepository vacationCommentRepository;
    private final AppUserRepository appUserRepository;
    private final VacationRepository vacationRepository;

@Override
public GetVacationCommentResponse makeCommentForVacation(MakeCommentForVacationRequest request) {
    Vacation vacation = vacationRepository.findById(request.getVacationId()).orElseThrow(() -> new RuntimeException("Vacation not found"));
    AppUser appUser = appUserRepository.findById(request.getAuthorId()).orElseThrow(() -> new RuntimeException("AppUser not found"));
    VacationComments vacationComments = VacationComments.builder()
    .comment(request.getComment())
    .rating(request.getRating())
    .vacation(vacation)
    .author(appUser)
    .build();
    vacationCommentRepository.save(vacationComments);
    AppUserResponse appUserResponse = AppUserResponse.builder()
    .id(appUser.getId())
    .firstName(appUser.getFirstName())
    .lastName(appUser.getLastName())
    .email(appUser.getEmail())
    .phoneNumber(appUser.getPhoneNumber())
    .build();
    LocalDateTime createdAt = vacationComments.getCreatedAt();
    return GetVacationCommentResponse.builder()
    .message("Vacation comment made successfully")
    .status("success")
    .code("200")
    .id(vacationComments.getId())
    .comment(vacationComments.getComment())
    .rating(vacationComments.getRating())
    .author(appUserResponse)
    .createdAt(createdAt)
    .build();
}


@Override
public List<GetVacationCommentResponse> getAllVacationComments(UUID vacationId) {
    List<VacationComments> vacationComments = vacationCommentRepository.findAllByVacationId(vacationId);
    List<GetVacationCommentResponse> responses = new ArrayList<>();

    for(VacationComments vacationComment : vacationComments){
        AppUser appUser = null;
        try{
        appUser = appUserRepository.findById(vacationComment.getAuthor().getId()).orElseThrow(() -> new RuntimeException("AppUser not found"));
        }catch(Exception e){
            System.out.println("AppUser not found"+e.getMessage());
            continue;
        }
        AppUserResponse appUserResponse = AppUserResponse.builder()
        .id(appUser.getId())
        .firstName(appUser.getFirstName())
        .profilePicture(appUser.getProfilePictureLink())
        .lastName(appUser.getLastName())
        .email(appUser.getEmail())
        .phoneNumber(appUser.getPhoneNumber())
        .build();
        LocalDateTime createdAt = vacationComment.getCreatedAt();
        responses.add(GetVacationCommentResponse.builder()
        .message("Vacation comment retrieved successfully")
        .status("success")
        .code("200")
        .id(vacationComment.getId())
        .comment(vacationComment.getComment())
        .rating(vacationComment.getRating())
        .author(appUserResponse)
        .createdAt(createdAt)
        .build());
    }
    return responses;
}
    
}
