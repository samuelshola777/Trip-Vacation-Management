package com.trip_excursion_management.vacation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeCommentForVacationRequest {
    private Long vacationId;
    private String comment;
    private int rating;
    private Long authorId;
    private Long vacationCommentId;
}

