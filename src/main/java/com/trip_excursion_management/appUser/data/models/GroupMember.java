package com.trip_excursion_management.appUser.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.ManyToOne;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Group group;

    private boolean isActive;

    private LocalDateTime createdAt;

    private boolean isAdmin;
}
