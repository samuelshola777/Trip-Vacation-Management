package com.trip_excursion_management.appUser.service.interfaces;
import com.trip_excursion_management.appUser.dtos.request.CreateGroupRequest;
import com.trip_excursion_management.appUser.dtos.response.CreateGroupResponse;
import com.trip_excursion_management.appUser.dtos.response.RegisterAppUserResponse;
import com.trip_excursion_management.appUser.dtos.request.RegisterAppUserRequest;
import com.trip_excursion_management.appUser.dtos.request.RemoveOrAddToGroupRequest;
import com.trip_excursion_management.appUser.dtos.response.GetGroupByIdResponse;


public interface AppUserService {
    
    RegisterAppUserResponse registerAppUser(RegisterAppUserRequest registerRequest);

    RegisterAppUserResponse updateAppUser(RegisterAppUserRequest updateRequest);

    RegisterAppUserResponse deleteAppUser(String appUserId);

    CreateGroupResponse createGroup(CreateGroupRequest createGroupRequest);

    CreateGroupResponse addAppUserToGroup(CreateGroupRequest addAppUserToGroupRequest);

    CreateGroupResponse updateGroup(CreateGroupRequest updateGroupRequest);

    CreateGroupResponse deleteGroup(CreateGroupRequest deleteGroupRequest);

    GetGroupByIdResponse getGroupMembers(CreateGroupRequest getGroupMembersRequest);

    CreateGroupResponse makeGroupMemberAdmin(RemoveOrAddToGroupRequest makeGroupMemberAdminRequest);

    CreateGroupResponse removeAppUserFromGroup(RemoveOrAddToGroupRequest removeAppUserFromGroupRequest);

}
