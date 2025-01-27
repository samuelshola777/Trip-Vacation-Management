package com.trip_excursion_management.appUser.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.appUser.models.AppUserRole;

public interface AppUserRoleRepository extends JpaRepository<AppUserRole, UUID> {

    Optional<AppUserRole> findByRoleName(String roleName);
    
}
