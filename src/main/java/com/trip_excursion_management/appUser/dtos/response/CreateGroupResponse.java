package com.trip_excursion_management.appUser.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateGroupResponse {
    private String message;
    private String groupId;
    private String groupName;
    private String description;
    private String groupImage;
    private String statusCode;
}
