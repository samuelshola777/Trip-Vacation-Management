package com.trip_excursion_management.vacation.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.trip_excursion_management.vacation.dto.request.MakeCommentForVacationRequest;
import com.trip_excursion_management.vacation.dto.response.GetVacationCommentResponse;

public interface VacationCommentService {
    //vacation comment/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

     GetVacationCommentResponse makeCommentForVacation(MakeCommentForVacationRequest request);
    List<GetVacationCommentResponse> getAllVacationComments(UUID vacationId);
    GetVacationCommentResponse getVacationCommentById(UUID vacationCommentId);
    GetVacationCommentResponse deleteVacationComment(UUID vacationCommentId);
    GetVacationCommentResponse updateVacationComment( MakeCommentForVacationRequest request);


    //display media comment/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    GetVacationCommentResponse makeCommentForDisplayMedia(MakeCommentForVacationRequest request);

    GetVacationCommentResponse updateCommentForDisplayMedia(MakeCommentForVacationRequest request);
    List<GetVacationCommentResponse> getAllVacationCommentsForDisplayMedia(UUID vacationId);
    GetVacationCommentResponse getVacationCommentForDisplayMediaById(UUID vacationCommentId);
    GetVacationCommentResponse deleteVacationCommentForDisplayMedia(UUID vacationCommentId);
}
