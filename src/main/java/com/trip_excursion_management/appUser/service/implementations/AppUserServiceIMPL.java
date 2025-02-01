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
import com.trip_excursion_management.appUser.data.repository.AppUserRepository;
import com.trip_excursion_management.appUser.data.repository.AppUserRoleControlRepository;
import com.trip_excursion_management.appUser.data.repository.AppUserRoleRepository;
import com.trip_excursion_management.appUser.dtos.response.RegisterAppUserResponse;

import java.util.List;
import com.trip_excursion_management.appUser.dtos.response.AppUserResponse;
import java.util.ArrayList;



import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserServiceIMPL implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserRoleRepository appUserRoleRepository;
    private final AppUserRoleControlRepository appUserRoleControlRepository;
  



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
    public AppUserResponse getAppUserById(UUID appUserId){
       AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(() -> new RuntimeException("App User not found"));
        return AppUserResponse.builder()
        .id(appUser.getId())
        .email(appUser.getEmail())
        .firstName(appUser.getFirstName())
        .lastName(appUser.getLastName())
        .phoneNumber(appUser.getPhoneNumber())
        .profilePicture(appUser.getProfilePictureLink())
        .appUserRoleControl(appUser.getAppUserRoleControl())
        .createdAt(appUser.getCreatedAt().toString())
        .isActive(appUser.isActive())
        .build();
    }

    @Override
    public List<AppUserResponse> getAllAppUsers(){
        List<AppUser> appUsers = appUserRepository.findAll();
       List<AppUserResponse> appUserResponses = new ArrayList<>();
       for(AppUser appUser : appUsers){
        appUserResponses.add(AppUserResponse.builder()
        .id(appUser.getId())
        .email(appUser.getEmail())
        .firstName(appUser.getFirstName())
        .lastName(appUser.getLastName())
        .build());
       }
       return appUserResponses;
    }

    }
