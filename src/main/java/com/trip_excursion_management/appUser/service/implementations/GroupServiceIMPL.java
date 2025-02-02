package com.trip_excursion_management.appUser.service.implementations;

import com.trip_excursion_management.appUser.service.interfaces.GroupService;
import com.trip_excursion_management.appUser.dtos.request.AddAppUserToGroupRequest;
import com.trip_excursion_management.appUser.dtos.request.CreateGroupRequest;
import com.trip_excursion_management.appUser.dtos.response.CreateGroupResponse;
import com.trip_excursion_management.appUser.dtos.response.GetGroupByIdResponse;
import com.trip_excursion_management.appUser.dtos.request.RemoveOrAddToGroupRequest;
import com.trip_excursion_management.appUser.data.models.Group;
import com.trip_excursion_management.appUser.data.models.GroupMember;
import com.trip_excursion_management.appUser.data.models.AppUser;
import com.trip_excursion_management.appUser.data.repository.GroupRepository;
import com.trip_excursion_management.appUser.data.repository.GroupMemberRepository;
import com.trip_excursion_management.appUser.data.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import com.trip_excursion_management.vacation.data.repositories.VacationPlanRepository;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import com.trip_excursion_management.vacation.data.model.VacationPlan;
import com.trip_excursion_management.vacation.data.model.VacationPlanStatus;

import java.util.Set;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class GroupServiceIMPL  implements GroupService{
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final AppUserRepository appUserRepository;
    private final VacationPlanRepository vacationPlanRepository;



    @Override
    public CreateGroupResponse createGroup(CreateGroupRequest createGroupRequest){
        //implement group image upload

       AppUser appUser = appUserRepository.findById(createGroupRequest.getAppUserId()).orElseThrow(() -> new RuntimeException("App User not found"));
       
        Group group = Group.builder()
        .groupName(createGroupRequest.getGroupName())
        .description(createGroupRequest.getDescription())
        .groupImage(createGroupRequest.getGroupImage())
        .createdAt(LocalDateTime.now())
        .isActive(true)
        .build();

        Group savedGroup = groupRepository.save(group);
        GroupMember groupMember = GroupMember.builder()
        .appUser(appUser)
        .group(savedGroup)
        .isActive(true)
        .createdAt(LocalDateTime.now())
        .isAdmin(true)
        .build();
        groupMemberRepository.save(groupMember);

        return CreateGroupResponse.builder()
        .message("Group created successfully")
        .groupName(savedGroup.getGroupName())
        .description(savedGroup.getDescription())
        .groupImage(savedGroup.getGroupImage())
        .statusCode("201")
        .groupId(savedGroup.getId().toString())
        .build();
    }



    @Override
    public CreateGroupResponse addAppUserToGroup(AddAppUserToGroupRequest addAppUserToGroupRequest){
        Set<AppUser> listOfAppUsersAddedToTheExistingVacationPlan = new HashSet<>();
        try{

        // check if the logged in user is an admin in the group
        Group group = groupRepository.findById(addAppUserToGroupRequest.getGroupId()).orElseThrow(() -> new RuntimeException("Group not found"));
    //check if the group already has a vacation plan 
        VacationPlan vacationPlan = vacationPlanRepository.findByGroup(group.getId());
    GroupMember isAppUserAdmin = groupMemberRepository.findByAppUserAndGroup(addAppUserToGroupRequest.getAppUserId(), addAppUserToGroupRequest.getGroupId());
    boolean isAppUserAdminInGroup = isAppUserAdmin != null && isAppUserAdmin.isAdmin();
  //check if the the user that is trying to add another user to the group is an admin in the group 
 if(!isAppUserAdminInGroup){
        throw new RuntimeException("You are not an admin in this group, you cannot add users to this group");
    }
   
if(addAppUserToGroupRequest.getAppUserEmail().size() != 0){

    for(String email : addAppUserToGroupRequest.getAppUserEmail()){
        AppUser appUser = appUserRepository.findByEmail(email).orElse(null);
        if(appUser != null){
       GroupMember isAppUserAlreadyInGroup = groupMemberRepository.findByAppUserAndGroup(appUser.getId(), addAppUserToGroupRequest.getGroupId());
        if(isAppUserAlreadyInGroup == null){
        GroupMember groupMember = GroupMember.builder()
        .appUser(appUser)
        .group(group)
        .isActive(true)
        .createdAt(LocalDateTime.now())
        .isAdmin(false)
        .build();
        groupMemberRepository.save(groupMember);
        if(isVacationPlanPending(vacationPlan)){
            listOfAppUsersAddedToTheExistingVacationPlan.add(appUser);
        }
        }
    }else{
        continue;
    }
    }
    }

    if(addAppUserToGroupRequest.getAppUserPhoneNumber().size() != 0){
        for(String phoneNumber : addAppUserToGroupRequest.getAppUserPhoneNumber()){
            AppUser appUser = appUserRepository.findByPhoneNumber(phoneNumber).orElse(null);
            if(appUser != null){
                GroupMember isAppUserAlreadyInGroup = groupMemberRepository.findByAppUserAndGroup(appUser.getId(), addAppUserToGroupRequest.getGroupId());
                 if(isAppUserAlreadyInGroup == null){
                 GroupMember groupMember = GroupMember.builder()
                 .appUser(appUser)
                 .group(group)
                 .isActive(true)
                 .createdAt(LocalDateTime.now())
                 .isAdmin(false)
                 .build();
                 groupMemberRepository.save(groupMember);
                 if(isVacationPlanPending(vacationPlan)){
                     listOfAppUsersAddedToTheExistingVacationPlan.add(appUser);
                 }
                 }
             }else{
                 continue;
             }
             }
    }
    if(listOfAppUsersAddedToTheExistingVacationPlan.size() != 0){
        // notify all the admin in the group
        // notify the app admin in charge of the vacation plan
    }
    listOfAppUsersAddedToTheExistingVacationPlan.clear();
    return CreateGroupResponse.builder()
    .message(listOfAppUsersAddedToTheExistingVacationPlan.size() + " App Users added to group successfully")
    .groupName(group.getGroupName())
    .description(group.getDescription())
    .groupImage(group.getGroupImage())
    .statusCode("200")
    .build();
   }catch(Exception e){
    listOfAppUsersAddedToTheExistingVacationPlan.clear();
    return CreateGroupResponse.builder()
    .message("Error adding App Users to group: " + e.getMessage())
    .statusCode("500")
    .build();
   }
    }

    @Override
    public CreateGroupResponse removeAppUserFromGroup(RemoveOrAddToGroupRequest removeAppUserFromGroupRequest){
               // check if the logged in user is an admin in the group
               Group group = groupRepository.findById(removeAppUserFromGroupRequest.getGroupId()).orElseThrow(() -> new RuntimeException("Group not found"));
               //check if the group already has a vacation plan 
                   VacationPlan vacationPlan = vacationPlanRepository.findByGroup(group.getId());
                 Set<AppUser> listOfAppUsersAddedToTheExistingVacationPlan = new HashSet<>();
        try{

      
        GroupMember isAppUserAdmin = groupMemberRepository.findByAppUserAndGroup(removeAppUserFromGroupRequest.getAppUserId(), removeAppUserFromGroupRequest.getGroupId());
        boolean isAppUserAdminInGroup = isAppUserAdmin != null && isAppUserAdmin.isAdmin();
      //check if the the user that is trying to add another user to the group is an admin in the group 
     if(!isAppUserAdminInGroup){
            throw new RuntimeException("You are not an admin in this group, you cannot add users to this group");
        }

        if(removeAppUserFromGroupRequest.getAppUserEmail().size() != 0){
            for(String email : removeAppUserFromGroupRequest.getAppUserEmail()){
                AppUser appUser = appUserRepository.findByEmail(email).orElse(null);
                if(appUser != null){
                    groupMemberRepository.deleteByAppUserAndGroup(appUser.getId(), removeAppUserFromGroupRequest.getGroupId());
                    if(isVacationPlanPending(vacationPlan)){
                        listOfAppUsersAddedToTheExistingVacationPlan.add(appUser);
                    }
                }
            }
        }
        if(removeAppUserFromGroupRequest.getAppUserPhoneNumber().size() != 0){
            for(String phoneNumber : removeAppUserFromGroupRequest.getAppUserPhoneNumber()){
                AppUser appUser = appUserRepository.findByPhoneNumber(phoneNumber).orElse(null);
                if(appUser != null){
                    groupMemberRepository.deleteByAppUserAndGroup(appUser.getId(), removeAppUserFromGroupRequest.getGroupId());
                    if(isVacationPlanPending(vacationPlan)){
                        listOfAppUsersAddedToTheExistingVacationPlan.add(appUser);
                    }
                }
            }
        }
        if(listOfAppUsersAddedToTheExistingVacationPlan.size() != 0){
            // notify all the admin in the group
            // notify the app admin in charge of the vacation plan
        }
        }catch(Exception e){
            listOfAppUsersAddedToTheExistingVacationPlan.clear();
            return CreateGroupResponse.builder()
            .message("Error removing App Users from group: " + e.getMessage())
            .statusCode("500")
            .build();
        }
       
        listOfAppUsersAddedToTheExistingVacationPlan.clear();
        return CreateGroupResponse.builder()
        .message( " App Users removed from group successfully")
        .groupName(group.getGroupName())
        .description(group.getDescription())
        .groupImage(group.getGroupImage())
        .statusCode("200")
        .build();
         }

    @Override
    public CreateGroupResponse updateGroup(CreateGroupRequest updateGroupRequest){
        Group group = groupRepository.findById(updateGroupRequest.getGroupId()).orElseThrow(() -> new RuntimeException("Group not found"));
        
        if (updateGroupRequest.getGroupName() != null && !updateGroupRequest.getGroupName().trim().isEmpty()) {
            group.setGroupName(updateGroupRequest.getGroupName());
        }
        
        if (updateGroupRequest.getDescription() != null && !updateGroupRequest.getDescription().trim().isEmpty()) {
            group.setDescription(updateGroupRequest.getDescription());
        }
        
        if (updateGroupRequest.getGroupImage() != null && !updateGroupRequest.getGroupImage().trim().isEmpty()) {
            group.setGroupImage(updateGroupRequest.getGroupImage());
        }
        
        groupRepository.save(group);
        return CreateGroupResponse.builder()
            .message("Group updated successfully")
            .groupId(group.getId().toString())
            .groupName(group.getGroupName())
            .description(group.getDescription())
            .groupImage(group.getGroupImage())
            .statusCode("200")
            .build();
    }

    @Override
    public CreateGroupResponse deleteGroup(CreateGroupRequest deleteGroupRequest) {
        try {
            groupMemberRepository.deleteAllByGroup(deleteGroupRequest.getGroupId());
            groupRepository.deleteById(deleteGroupRequest.getGroupId());
            
            return CreateGroupResponse.builder()
                .message("Group deleted successfully")
                .groupId(deleteGroupRequest.getGroupId().toString())
                .groupName("Group Name")
                .description("Group Description")
                .groupImage("Group Image")
                .statusCode("200")
                .build();

        } catch (Exception e) {
            return CreateGroupResponse.builder()
                .message("Error deleting group: " + e.getMessage())
                .groupId(deleteGroupRequest.getGroupId().toString())
                .statusCode("500")
                .build();
        }
    }
    
    @Override
    public CreateGroupResponse makeGroupMemberAdmin(RemoveOrAddToGroupRequest request) {

        AppUser appUser = null;
        GroupMember groupMember = null;
      try{
        if(request.getAppUserPhoneNumber().size() != 0){
            for(String phoneNumber : request.getAppUserPhoneNumber()){
                appUser = appUserRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new RuntimeException("App User not found"));
                groupMember = groupMemberRepository.findByAppUserAndGroup(appUser.getId(), request.getGroupId());
                if(groupMember == null){
                    throw new RuntimeException("Group Member not found");
                }
                groupMember.setAdmin(true);
                groupMemberRepository.save(groupMember);
            }
        }
      }catch(Exception e){
        System.err.println("Error promoting members to admin: " + e.getMessage());
      }

        try {
            for(String email : request.getAppUserEmail()) {
                appUser = appUserRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("App User not found"));
                    
                 groupMember = groupMemberRepository.findByAppUserAndGroup(appUser.getId(), request.getGroupId());
                 if(groupMember == null){
                    throw new RuntimeException("Group Member not found");
                 }
                
                groupMember.setAdmin(true);
                groupMemberRepository.save(groupMember);
            }
            
            return CreateGroupResponse.builder()
                .message("Group members promoted to admin successfully")
                .statusCode("200")
                .build();
                
        } catch (Exception e) {
            return CreateGroupResponse.builder()
                .message("Error promoting members to admin: " + e.getMessage())
                .statusCode("500")
                .build();
        }
    }

    @Override
    public GetGroupByIdResponse getGroupMembers(CreateGroupRequest getGroupMembersRequest) {
        Group group = groupRepository.findById(getGroupMembersRequest.getGroupId()).orElseThrow(() -> new RuntimeException("Group not found"));
        List<GroupMember> groupMembers = groupMemberRepository.findAllByGroup(group.getId());
        return GetGroupByIdResponse.builder()
        .message("Group members fetched successfully")
        .statusCode("200")
        .groupMembers(groupMembers)
        .group(group)
        .build();
    }
    @Override
    public Group getGroup(UUID id){
        return groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found"));
    }
    private boolean isVacationPlanPending(VacationPlan vacationPlan){
        if(vacationPlan != null && vacationPlan.getVacationPlanStatus() != VacationPlanStatus.PENDING || vacationPlan != null && vacationPlan.getVacationPlanStatus() != VacationPlanStatus.CANCELLED || vacationPlan != null && vacationPlan.getVacationPlanStatus() != VacationPlanStatus.REJECTED || vacationPlan != null && vacationPlan.getVacationPlanStatus() != VacationPlanStatus.COMPLETED){
            return false;
        }
        return true;
    }
}
