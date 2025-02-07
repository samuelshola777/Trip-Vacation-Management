package com.trip_excursion_management.vacation.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.trip_excursion_management.appUser.dtos.response.AppUserResponse;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetVacationCommentResponse {
    private Long id;
    private String comment;
    private AppUserResponse author;
    private int rating;
    private LocalDateTime createdAt;
    private String message;

    private String status;
    private String code;
    private Long vacationId;



}
