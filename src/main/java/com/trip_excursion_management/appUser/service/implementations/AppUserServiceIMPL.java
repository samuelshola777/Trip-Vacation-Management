package com.trip_excursion_management.appUser.service.implementations;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.trip_excursion_management.appUser.service.interfaces.AppUserService;
import com.trip_excursion_management.appUser.dtos.request.RegisterAppUserRequest;
import com.trip_excursion_management.appUser.data.models.AppUser;
import com.trip_excursion_management.appUser.data.models.AppUserRole;
import com.trip_excursion_management.appUser.data.models.AppUserRoleControl;
import com.trip_excursion_management.appUser.data.models.Group;
import com.trip_excursion_management.appUser.data.models.GroupMember;
import com.trip_excursion_management.appUser.data.repository.AppUserRepository;
import com.trip_excursion_management.appUser.data.repository.AppUserRoleControlRepository;
import com.trip_excursion_management.appUser.data.repository.AppUserRoleRepository;
import com.trip_excursion_management.appUser.data.repository.GroupMemberRepository;
import com.trip_excursion_management.appUser.data.repository.GroupRepository;
import com.trip_excursion_management.appUser.dtos.request.CreateGroupRequest;
import com.trip_excursion_management.appUser.dtos.request.RemoveOrAddToGroupRequest;
import com.trip_excursion_management.appUser.dtos.response.RegisterAppUserResponse;
import com.trip_excursion_management.appUser.dtos.response.CreateGroupResponse;
import com.trip_excursion_management.appUser.dtos.response.GetGroupByIdResponse;
import java.util.List;


import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserServiceIMPL implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final AppUserRoleControlRepository appUserRoleControlRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;



@Override
public RegisterAppUserResponse registerAppUser(RegisterAppUserRequest registerRequest){

// check if the email is already in use
// check if the phone number is already in use
// check if the first name and last name are valid
// check if the password is valid
// check if the profile picture link is valid
try{
    AppUser appUser = AppUser.builder()
    .email(registerRequest.getEmail())
    .password(registerRequest.getPassword())
    .firstName(registerRequest.getFirstName())
    .lastName(registerRequest.getLastName())
    .createdAt(LocalDateTime.now())
    .phoneNumber(registerRequest.getPhoneNumber())
    .profilePictureLink(registerRequest.getProfilePictureLink())
    .build();
    // save the app user
    AppUser savedAppUser = appUserRepository.save(appUser);
    // create the app user role control
    AppUserRoleControl appUserRoleControl = AppUserRoleControl.builder()
    .appUser(savedAppUser)
    .appUserRole(Set.of(appUserRoleRepository.findByRoleName("APP_USER").orElseThrow(() -> new RuntimeException("App User Role not found"))))
    .createdAt(LocalDateTime.now())
    .updatedAt(LocalDateTime.now())
    .isActive(true)
    .build();
    // save the app user role control
  appUserRoleControlRepository.save(appUserRoleControl);
  // return the response
    return RegisterAppUserResponse.builder()
            .message("User registered successfully")
            .appUserId(appUser.getId().toString())
            .appUserEmail(appUser.getEmail())
            .appUserFirstName(appUser.getFirstName())
            .appUserLastName(appUser.getLastName())
            .appUserPhoneNumber(appUser.getPhoneNumber())
            .appUserProfilePictureLink(appUser.getProfilePictureLink())
            .appUserRole("APP_USER")
            .statusCode("201")
            .build();
}catch(Exception e){
    throw new RuntimeException("Error registering app user");
}

}


@Override
public RegisterAppUserResponse updateAppUser(RegisterAppUserRequest updateRequest){

    AppUser appUser = appUserRepository.findById(updateRequest.getAppUserId()).orElseThrow(() -> new RuntimeException("App User not found"));
     
    
    if (updateRequest.getFirstName() != null && !updateRequest.getFirstName().trim().isEmpty()) {
        appUser.setFirstName(updateRequest.getFirstName());
    }
    
    if (updateRequest.getLastName() != null && !updateRequest.getLastName().trim().isEmpty()) {
        appUser.setLastName(updateRequest.getLastName());
    }
    
    if (updateRequest.getPhoneNumber() != null && !updateRequest.getPhoneNumber().trim().isEmpty()) {
        appUser.setPhoneNumber(updateRequest.getPhoneNumber());
    }
    
    if (updateRequest.getProfilePictureLink() != null && !updateRequest.getProfilePictureLink().trim().isEmpty()) {
        appUser.setProfilePictureLink(updateRequest.getProfilePictureLink());
    }

    AppUser updatedUser = appUserRepository.save(appUser);

    return RegisterAppUserResponse.builder()
            .message("User updated successfully")
            .appUserId(updatedUser.getId().toString())
            .appUserEmail(updatedUser.getEmail())
            .appUserFirstName(updatedUser.getFirstName())
            .appUserLastName(updatedUser.getLastName())
            .appUserPhoneNumber(updatedUser.getPhoneNumber())
            .appUserProfilePictureLink(updatedUser.getProfilePictureLink())
            .statusCode("200")
            .build();
}

@Override
public RegisterAppUserResponse deleteAppUser(String appUserId){
    AppUser appUser = appUserRepository.findById(UUID.fromString(appUserId)).orElseThrow(() -> new RuntimeException("App User not found"));
    AppUserRoleControl appUserRoleControl = appUserRoleControlRepository.findByAppUser(appUser.getId()).orElseThrow(() -> new RuntimeException("App User Role Control not found"));
    appUserRoleControlRepository.delete(appUserRoleControl);
    appUserRepository.delete(appUser);
    return RegisterAppUserResponse.builder()
            .message("User deleted successfully")
            .appUserId(appUser.getId().toString())
            .statusCode("200")
            .build();
}





@PostConstruct
    private void seedRoles(){
        // seed the roles
        // check if the role exists
        // if not, create the role
        // save the role
        AppUserRole appAdmin = appUserRoleRepository.findByRoleName("APP_ADMIN").orElse(null);
        if (appAdmin == null) {
            appAdmin = AppUserRole.builder()
            .roleName("APP_ADMIN")
            .description("App Admin Role")
            .isActive(true)
            .build();
            appUserRoleRepository.save(appAdmin);
        }
        
        AppUserRole appUser = appUserRoleRepository.findByRoleName("APP_USER").orElse(null);
        if (appUser == null) {
            appUser = AppUserRole.builder()
            .roleName("APP_USER")
            .description("App User Role")
            .isActive(true)
            .build();
            appUserRoleRepository.save(appUser);
        }

        AppUserRole appSuperAdmin = appUserRoleRepository.findByRoleName("APP_SUPER_ADMIN").orElse(null);
        if (appSuperAdmin == null) {
            appSuperAdmin = AppUserRole.builder()
            .roleName("APP_SUPER_ADMIN")
            .description("App Super Admin Role")
            .isActive(true)
            .build();
            appUserRoleRepository.save(appSuperAdmin);
        }

        AppUserRole groupAdmin = appUserRoleRepository.findByRoleName("GROUP_ADMIN").orElse(null);
        if (groupAdmin == null) {
            groupAdmin = AppUserRole.builder()
            .roleName("GROUP_ADMIN")
            .description("Group Admin Role")
            .isActive(true)
            .build();
            appUserRoleRepository.save(groupAdmin);
        }
    }

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
    public CreateGroupResponse addAppUserToGroup(CreateGroupRequest addAppUserToGroupRequest){
         AppUser appUser = null;
         Group group = null;
         try{
            if(addAppUserToGroupRequest.getAppUserPhoneNumber().size() != 0){
                for(String phoneNumber : addAppUserToGroupRequest.getAppUserPhoneNumber()){
                    appUser = appUserRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new RuntimeException("App User not found"));
                    group = groupRepository.findById(addAppUserToGroupRequest.getGroupId()).orElseThrow(() -> new RuntimeException("Group not found"));
                    boolean isMember = groupMemberRepository.existsByAppUserAndGroup(appUser.getId(), group.getId());
                    if(!isMember) {
                        GroupMember groupMember = GroupMember.builder()
                        .appUser(appUser)
                        .group(group)
                        .isActive(true)
                        .createdAt(LocalDateTime.now())
                        .isAdmin(false)
                        .build();
                        groupMemberRepository.save(groupMember);
                    }
                }
            }
         }
         catch(Exception e){
            System.err.println("Error adding users to group: " + e.getMessage());
         }
        if(addAppUserToGroupRequest.getAppUserEmail().size() != 0){
            for(String email : addAppUserToGroupRequest.getAppUserEmail()){
              
                try{
                     appUser = appUserRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("App User not found"));
                     group = groupRepository.findById(addAppUserToGroupRequest.getGroupId()).orElseThrow(() -> new RuntimeException("Group not found"));
                    
                }catch(Exception e){
                    System.err.println("Error adding user with email " + email + ": " + e.getMessage());
                    continue;
                }
                
                boolean isMember = groupMemberRepository.existsByAppUserAndGroup(appUser.getId(), group.getId());
               
                if(!isMember) {
                    GroupMember groupMember = GroupMember.builder()
                    .appUser(appUser)
                    .group(group)
                    .isActive(true)
                    .createdAt(LocalDateTime.now())
                    .isAdmin(false)
                    .build();
                    groupMemberRepository.save(groupMember);
                }
            }
        }
        if(group == null){
            return CreateGroupResponse.builder()
            .message("Group not found")
            .statusCode("404")
            .build();
        }
        return CreateGroupResponse.builder()
        .message("App Users added to group successfully")
        .groupName(group.getGroupName())
        .description(group.getDescription())
        .groupImage(group.getGroupImage())
        .statusCode("201")
        .build();
    }

    @Override
    public CreateGroupResponse removeAppUserFromGroup(RemoveOrAddToGroupRequest removeAppUserFromGroupRequest){
        AppUser appUser = null;
        try{
             if(removeAppUserFromGroupRequest.getAppUserPhoneNumber().size() != 0){
            for(String phoneNumber : removeAppUserFromGroupRequest.getAppUserPhoneNumber()){
                appUser = appUserRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new RuntimeException("App User not found"));
                groupMemberRepository.deleteByAppUserAndGroup(appUser.getId(), removeAppUserFromGroupRequest.getGroupId());
            }
        }
        }catch(Exception e){
            System.err.println("Error removing users from group: " + e.getMessage());
        }
       

        if(removeAppUserFromGroupRequest.getAppUserEmail().size() != 0){
            for(String email : removeAppUserFromGroupRequest.getAppUserEmail()){
                try {
                    appUser = appUserRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("App User not found"));
                        groupMemberRepository.deleteByAppUserAndGroup(appUser.getId(), removeAppUserFromGroupRequest.getGroupId());
                }catch(Exception e){
                    System.err.println("Error removing users from group: " + e.getMessage());
                }
            }
        }

          
            return CreateGroupResponse.builder()
                .message("App Users removed from group successfully") 
                .groupName("Group Name")
                .description("Group Description")
                .groupImage("Group Image")
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
                groupMember = groupMemberRepository.findByAppUserAndGroup(appUser.getId(), request.getGroupId()).orElseThrow(() -> new RuntimeException("Group Member not found"));
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
                    
                 groupMember = groupMemberRepository.findByAppUserAndGroup(appUser.getId(), request.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Group Member not found"));
                
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
}
