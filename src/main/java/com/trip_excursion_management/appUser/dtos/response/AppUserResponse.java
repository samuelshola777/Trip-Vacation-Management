package com.trip_excursion_management.appUser.dtos.response;

import java.util.UUID;

import com.trip_excursion_management.appUser.data.models.AppUserRoleControl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String profilePicture;
    private String createdAt;
    private boolean isActive;
    private AppUserRoleControl appUserRoleControl;
}
