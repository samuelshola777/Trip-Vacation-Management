package com.trip_excursion_management.appUser.dtos.request;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterAppUserRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String profilePictureLink;
    private UUID appUserId;
    
}