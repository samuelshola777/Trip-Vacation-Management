package com.trip_excursion_management.vacation.service.implementation;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.trip_excursion_management.vacation.data.model.Vacation;
import com.trip_excursion_management.vacation.data.model.VacationDisplayMedia;
import com.trip_excursion_management.vacation.data.repositories.VacationDisplayMediaRepository;
import com.trip_excursion_management.vacation.data.repositories.VacationRepository;
import com.trip_excursion_management.vacation.dto.request.AddMediaRequest;
import com.trip_excursion_management.vacation.dto.response.CreateVacationResponse;
import com.trip_excursion_management.vacation.service.interfaces.VacationDisplayMediaService;
import lombok.RequiredArgsConstructor;
import com.trip_excursion_management.vacation.dto.response.GetVacationDisplayMediaResponse;
import com.trip_excursion_management.vacation.dto.response.GetVacationCommentResponse;
import com.trip_excursion_management.vacation.data.model.VacationComments;
import java.util.ArrayList;
import java.util.List;
import com.trip_excursion_management.appUser.dtos.response.AppUserResponse;
import com.trip_excursion_management.appUser.data.models.AppUser;
@Service
@RequiredArgsConstructor

public class VacationDisplayMediaServiceIMPL  implements VacationDisplayMediaService{
   private final VacationRepository vacationRepository;
    private final VacationDisplayMediaRepository vacationDisplayMediaRepository;
@Override
public CreateVacationResponse uploadVacationDisplayMedia(AddMediaRequest request) {
    Vacation vacation = vacationRepository.findById(request.getVacationId()).orElseThrow(() -> new RuntimeException("Vacation not found"));
    //upload media to s3
    VacationDisplayMedia vacationDisplayMedia = VacationDisplayMedia.builder()
    .mediaUrl(request.getMedia().getOriginalFilename())
    .description(request.getDescription())
    .vacation(vacation)
    .build();
    vacationDisplayMediaRepository.save(vacationDisplayMedia);
    return CreateVacationResponse.builder()
    .message("Vacation display media uploaded successfully")
    .status("success")
    .code("200")
    .id(vacationDisplayMedia.getId())
    .build();

}

@Override
 public List<GetVacationDisplayMediaResponse> getAllVacationDisplayMedia(Long vacationId){
    List<VacationDisplayMedia> listOfVacationMedia = vacationDisplayMediaRepository.findAllByVacationId(vacationId);
    List<GetVacationDisplayMediaResponse> responses = new ArrayList<>();


    for (VacationDisplayMedia media : listOfVacationMedia) {
        
        GetVacationDisplayMediaResponse responseResponse = GetVacationDisplayMediaResponse.builder()
        .description(media.getDescription())
        .id(media.getId())
        .vacationMediaComments(mapVacationCommentToVacationCommentResponse(media.getVacationMediaComments()))
        .build();
        responses.add(responseResponse);
    }
    return responses;
}

private List<GetVacationCommentResponse> mapVacationCommentToVacationCommentResponse(List<VacationComments> vacationComments){
    List<GetVacationCommentResponse> responses = new ArrayList<>();
    for (VacationComments comment : vacationComments) {
        GetVacationCommentResponse response = GetVacationCommentResponse.builder()
        .id(comment.getId())
        .comment(comment.getComment())
        .rating(comment.getRating())
        .createdAt(comment.getCreatedAt())
        .author(mapAppUserToAppUserResponse(comment.getAuthor()))
        .message("Comment created successfully")
        .status("success")
        .code("200")
        .build();

        responses.add(response);

    }
    return responses;
}

private AppUserResponse mapAppUserToAppUserResponse(AppUser appUser){
    return AppUserResponse.builder()
    .id(appUser.getId())
    .email(appUser.getEmail())
    .firstName(appUser.getFirstName())
    .lastName(appUser.getLastName())
    .phoneNumber(appUser.getPhoneNumber())
    .profilePicture(appUser.getProfilePictureLink())
    .isActive(appUser.isActive())
    .appUserRoleControl(appUser.getAppUserRoleControl())
    .build();


}
}

