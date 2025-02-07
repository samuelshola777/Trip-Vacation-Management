package com.trip_excursion_management.appUser.data.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import com.trip_excursion_management.appUser.data.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    

    Optional<AppUser> findByPhoneNumber(String phoneNumber);
    Optional<AppUser> findByEmailEqualsIgnoreCase(String email);

}
