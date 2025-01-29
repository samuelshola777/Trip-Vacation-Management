package com.trip_excursion_management.appUser.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.trip_excursion_management.appUser.data.models.Group;
import com.trip_excursion_management.appUser.data.models.GroupMember;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetGroupByIdResponse {
    private String message;
    private String statusCode;
    private List<GroupMember> groupMembers;
    private Group group;
}
