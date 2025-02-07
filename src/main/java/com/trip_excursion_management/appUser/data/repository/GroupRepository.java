package com.trip_excursion_management.appUser.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.appUser.data.models.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    
}
