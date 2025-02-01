package com.trip_excursion_management.vacation.dto.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeCommentForVacationRequest {
    private UUID vacationId;
    private String comment;
    private int rating;
    private UUID authorId;
    private UUID vacationCommentId;
}
