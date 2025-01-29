package com.trip_excursion_management.appUser.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRoleControl {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne

    @JoinColumn(name = "app_user_id", nullable = false, unique = true)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "app_user_role_id")
    private Set<AppUserRole> appUserRole;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;
}
