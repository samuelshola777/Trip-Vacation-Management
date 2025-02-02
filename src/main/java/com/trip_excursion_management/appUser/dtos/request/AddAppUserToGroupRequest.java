package com.trip_excursion_management.appUser.dtos.request;

import java.util.Set;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddAppUserToGroupRequest {
    private UUID groupId;
     private Set<String> appUserEmail;
    private Set<String> appUserPhoneNumber;
    private UUID appUserId;
    
}