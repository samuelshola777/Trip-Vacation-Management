package com.trip_excursion_management.appUser.service.interfaces;

import com.trip_excursion_management.appUser.data.models.Group;
import com.trip_excursion_management.appUser.dtos.request.AddAppUserToGroupRequest;
import com.trip_excursion_management.appUser.dtos.request.CreateGroupRequest;
import com.trip_excursion_management.appUser.dtos.response.CreateGroupResponse;
import com.trip_excursion_management.appUser.dtos.response.GetGroupByIdResponse;
import com.trip_excursion_management.appUser.dtos.request.RemoveOrAddToGroupRequest;





public interface GroupService {

    CreateGroupResponse createGroup(CreateGroupRequest createGroupRequest);

    Group getGroup(Long id);

    CreateGroupResponse updateGroup(CreateGroupRequest updateGroupRequest);


    CreateGroupResponse addAppUserToGroup(AddAppUserToGroupRequest addAppUserToGroupRequest);


    CreateGroupResponse deleteGroup(CreateGroupRequest deleteGroupRequest);

    GetGroupByIdResponse getGroupMembers(CreateGroupRequest getGroupMembersRequest);

    CreateGroupResponse makeGroupMemberAdmin(RemoveOrAddToGroupRequest makeGroupMemberAdminRequest);

    CreateGroupResponse removeAppUserFromGroup(RemoveOrAddToGroupRequest removeAppUserFromGroupRequest);
}
