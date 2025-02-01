package com.trip_excursion_management.appUser.service.interfaces;
import com.trip_excursion_management.appUser.dtos.response.AppUserResponse;
import com.trip_excursion_management.appUser.dtos.response.RegisterAppUserResponse;
import com.trip_excursion_management.appUser.dtos.request.RegisterAppUserRequest;

import java.util.UUID;
import java.util.List;

public interface AppUserService {
    
    RegisterAppUserResponse registerAppUser(RegisterAppUserRequest registerRequest);

    RegisterAppUserResponse updateAppUser(RegisterAppUserRequest updateRequest);

    RegisterAppUserResponse deleteAppUser(String appUserId);
    AppUserResponse getAppUserById(UUID appUserId);
    List<AppUserResponse> getAllAppUsers();

   

}
