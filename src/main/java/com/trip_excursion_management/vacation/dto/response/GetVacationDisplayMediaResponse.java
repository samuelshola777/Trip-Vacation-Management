package com.trip_excursion_management.vacation.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetVacationDisplayMediaResponse {
    private UUID id;
    private String mediaUrl;
    private String description;
    private int rating;
    private List<GetVacationCommentResponse> vacationMediaComments;
}
