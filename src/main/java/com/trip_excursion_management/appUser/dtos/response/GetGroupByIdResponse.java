package com.trip_excursion_management.appUser.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.trip_excursion_management.appUser.models.GroupMember;
import com.trip_excursion_management.appUser.models.Group;
import java.util.List;

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
