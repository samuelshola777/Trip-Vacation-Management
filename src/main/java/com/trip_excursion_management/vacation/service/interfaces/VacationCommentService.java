package com.trip_excursion_management.vacation.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.trip_excursion_management.vacation.dto.request.MakeCommentForVacationRequest;
import com.trip_excursion_management.vacation.dto.response.GetVacationCommentResponse;

public interface VacationCommentService {
    

     GetVacationCommentResponse makeCommentForVacation(MakeCommentForVacationRequest request);
    List<GetVacationCommentResponse> getAllVacationComments(UUID vacationId);
}
