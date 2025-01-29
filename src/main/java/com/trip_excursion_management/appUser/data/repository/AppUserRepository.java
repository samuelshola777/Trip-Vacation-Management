package com.trip_excursion_management.appUser.data.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.trip_excursion_management.appUser.data.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    @NonNull
    Optional<AppUser> findById(@NonNull UUID id);
    Optional<AppUser> findByPhoneNumber(String phoneNumber);
}
