package com.trip_excursion_management.appUser.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.trip_excursion_management.appUser.service.interfaces.AppUserService;
import com.trip_excursion_management.appUser.dtos.request.RegisterAppUserRequest;
import com.trip_excursion_management.appUser.dtos.response.RegisterAppUserResponse;
import com.trip_excursion_management.appUser.dtos.response.AppUserResponse;
import java.util.UUID;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/api/app-user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;
    @PostMapping("/register")
    public ResponseEntity<RegisterAppUserResponse> registerAppUser(@RequestBody RegisterAppUserRequest request){
        return ResponseEntity.ok(appUserService.registerAppUser(request));
    }
    @PostMapping("/update")
    public ResponseEntity<RegisterAppUserResponse> updateAppUser(@RequestBody RegisterAppUserRequest request){
        return ResponseEntity.ok(appUserService.updateAppUser(request));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<RegisterAppUserResponse> deleteAppUser(@RequestBody String appUserId){
        return ResponseEntity.ok(appUserService.deleteAppUser(appUserId));
    }
    @GetMapping("/get-by-id")
    public ResponseEntity<AppUserResponse> getAppUserById(@RequestBody UUID appUserId){
        return ResponseEntity.ok(appUserService.getAppUserById(appUserId));
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<AppUserResponse>> getAllAppUsers(){
        return ResponseEntity.ok(appUserService.getAllAppUsers());
    }
    

}


