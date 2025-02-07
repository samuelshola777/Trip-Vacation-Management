package com.trip_excursion_management.vacation.service.interfaces;

import java.util.List;


import com.trip_excursion_management.vacation.dto.request.MakeCommentForVacationRequest;
import com.trip_excursion_management.vacation.dto.response.GetVacationCommentResponse;

public interface VacationCommentService {
    //vacation comment/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    GetVacationCommentResponse makeCommentForVacation(MakeCommentForVacationRequest request);
    List<GetVacationCommentResponse> getAllVacationComments(Long vacationId);
    GetVacationCommentResponse getVacationCommentById(Long vacationCommentId);
    GetVacationCommentResponse deleteVacationComment(Long vacationCommentId);
    GetVacationCommentResponse updateVacationComment( MakeCommentForVacationRequest request);



    //display media comment/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    GetVacationCommentResponse makeCommentForDisplayMedia(MakeCommentForVacationRequest request);

    GetVacationCommentResponse updateCommentForDisplayMedia(MakeCommentForVacationRequest request);
    List<GetVacationCommentResponse> getAllVacationCommentsForDisplayMedia(Long vacationId);
    GetVacationCommentResponse getVacationCommentForDisplayMediaById(Long vacationCommentId);
    GetVacationCommentResponse deleteVacationCommentForDisplayMedia(Long vacationCommentId);
}
