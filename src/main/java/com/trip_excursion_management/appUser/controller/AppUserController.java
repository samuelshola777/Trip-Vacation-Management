package com.trip_excursion_management.appUser.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.trip_excursion_management.appUser.service.interfaces.AppUserService;
import com.trip_excursion_management.appUser.dtos.request.RegisterAppUserRequest;
import com.trip_excursion_management.appUser.dtos.response.RegisterAppUserResponse;
import com.trip_excursion_management.appUser.dtos.request.CreateGroupRequest;
import com.trip_excursion_management.appUser.dtos.response.CreateGroupResponse;
import com.trip_excursion_management.appUser.dtos.request.RemoveOrAddToGroupRequest;
import com.trip_excursion_management.appUser.dtos.response.GetGroupByIdResponse;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/app-user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<RegisterAppUserResponse> registerAppUser(@RequestBody RegisterAppUserRequest request){
        return ResponseEntity.ok(appUserService.registerAppUser(request));
    }

    @PostMapping("/create-group")
    public ResponseEntity<CreateGroupResponse> createGroup(@RequestBody CreateGroupRequest request){
        return ResponseEntity.ok(appUserService.createGroup(request));
    }

    @PostMapping("/add-to-group")
    public ResponseEntity<CreateGroupResponse> addToGroup(@RequestBody CreateGroupRequest request){
        return ResponseEntity.ok(appUserService.addAppUserToGroup(request));
    }

    @PostMapping("/make-group-member-admin")
    public ResponseEntity<CreateGroupResponse> makeGroupMemberAdmin(@RequestBody RemoveOrAddToGroupRequest request){
        return ResponseEntity.ok(appUserService.makeGroupMemberAdmin(request));
    }

    @PostMapping("/remove-group-member")
    public ResponseEntity<CreateGroupResponse> removeGroupMember(@RequestBody RemoveOrAddToGroupRequest request){
        return ResponseEntity.ok(appUserService.removeAppUserFromGroup(request));
    }

    @PostMapping("/update-group")
    public ResponseEntity<CreateGroupResponse> updateGroup(@RequestBody CreateGroupRequest request){
        return ResponseEntity.ok(appUserService.updateGroup(request));
    }

    @PostMapping("/delete-group")
    public ResponseEntity<CreateGroupResponse> deleteGroup(@RequestBody CreateGroupRequest request){
        return ResponseEntity.ok(appUserService.deleteGroup(request));
    }
    @GetMapping("/get-group-members")
    public ResponseEntity<GetGroupByIdResponse> getGroupMembers(@RequestBody CreateGroupRequest request){
        return ResponseEntity.ok(appUserService.getGroupMembers(request));
    }
}


