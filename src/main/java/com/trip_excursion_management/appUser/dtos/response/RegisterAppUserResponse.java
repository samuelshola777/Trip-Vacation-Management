package com.trip_excursion_management.appUser.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterAppUserResponse {
    private String message;
    private String appUserId;
    private String appUserEmail;
    private String appUserFirstName;
    private String appUserLastName;
    private String appUserPhoneNumber;
    private String appUserProfilePictureLink;
    private String appUserRole;
    private String statusCode;
}