package com.trip_excursion_management.appUser.dtos.request;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RemoveOrAddToGroupRequest {
    private Set<String> appUserEmail;
    private Set<String> appUserPhoneNumber;
    private UUID groupId;
    private Set<UUID> appUserIds;
}
