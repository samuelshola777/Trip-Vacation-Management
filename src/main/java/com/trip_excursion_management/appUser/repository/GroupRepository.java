package com.trip_excursion_management.appUser.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.appUser.models.Group;

public interface GroupRepository extends JpaRepository<Group, UUID> {
    
}
