package com.trip_excursion_management.appUser.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.trip_excursion_management.appUser.service.interfaces.GroupService;
import com.trip_excursion_management.appUser.dtos.request.CreateGroupRequest;
import com.trip_excursion_management.appUser.dtos.request.RemoveOrAddToGroupRequest;
import com.trip_excursion_management.appUser.dtos.response.CreateGroupResponse;
import com.trip_excursion_management.appUser.dtos.response.GetGroupByIdResponse;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

   @PostMapping("/create-group")
    public ResponseEntity<CreateGroupResponse> createGroup(@RequestBody CreateGroupRequest request){
        return ResponseEntity.ok(groupService.createGroup(request));
    }

    @PostMapping("/add-to-group")
    public ResponseEntity<CreateGroupResponse> addToGroup(@RequestBody CreateGroupRequest request){
        return ResponseEntity.ok(groupService.addAppUserToGroup(request));
    }

    @PostMapping("/make-group-member-admin")
    public ResponseEntity<CreateGroupResponse> makeGroupMemberAdmin(@RequestBody RemoveOrAddToGroupRequest request){
        return ResponseEntity.ok(groupService.makeGroupMemberAdmin(request));
    }

    @PostMapping("/remove-group-member")
    public ResponseEntity<CreateGroupResponse> removeGroupMember(@RequestBody RemoveOrAddToGroupRequest request){
        return ResponseEntity.ok(groupService.removeAppUserFromGroup(request));
    }

    @PostMapping("/update-group")
    public ResponseEntity<CreateGroupResponse> updateGroup(@RequestBody CreateGroupRequest request){
        return ResponseEntity.ok(groupService.updateGroup(request));
    }

    @PostMapping("/delete-group")
    public ResponseEntity<CreateGroupResponse> deleteGroup(@RequestBody CreateGroupRequest request){
        return ResponseEntity.ok(groupService.deleteGroup(request));
    }
    @GetMapping("/get-group-members")
    public ResponseEntity<GetGroupByIdResponse> getGroupMembers(@RequestBody CreateGroupRequest request){
        return ResponseEntity.ok(groupService.getGroupMembers(request));
    }
}
