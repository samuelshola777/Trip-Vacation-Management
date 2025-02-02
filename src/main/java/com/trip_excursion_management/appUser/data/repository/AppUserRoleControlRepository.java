package com.trip_excursion_management.appUser.data.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip_excursion_management.appUser.data.models.AppUserRoleControl;

@Repository
public interface AppUserRoleControlRepository extends JpaRepository<AppUserRoleControl, UUID> {

    Optional<AppUserRoleControl> findByAppUser(UUID appUserId);
    
}
