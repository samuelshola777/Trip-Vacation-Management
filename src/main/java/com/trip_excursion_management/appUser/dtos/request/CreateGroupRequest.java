package com.trip_excursion_management.appUser.dtos.request;

import java.util.Set;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateGroupRequest {
    private String groupName;
    private String description;
    private String groupImage;
    private Set<String> appUserEmail;
    private Set<String> appUserPhoneNumber;
    private UUID groupId;
    private UUID appUserId;
}
