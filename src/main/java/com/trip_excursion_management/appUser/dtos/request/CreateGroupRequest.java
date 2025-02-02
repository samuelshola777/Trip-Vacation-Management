package com.trip_excursion_management.appUser.dtos.request;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateGroupRequest {
    private String groupName;
    private String description;
    private String groupImage;
    private UUID appUserId;
    private UUID groupId;
   
    
}
