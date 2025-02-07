package com.trip_excursion_management.appUser.data.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.stream.Collectors;
@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String email;

    private String profilePictureLink;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

   @OneToOne(mappedBy = "appUser")
   private AppUserRoleControl appUserRoleControl;

   private boolean isActive;

   private LocalDateTime createdAt;

   @Override
   public String getUsername() {
       return email;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return mapRolesToAuthorities();
   }

   @Override
   public boolean isAccountNonExpired() {
       return isActive;
   }

   @Override
   public boolean isAccountNonLocked() {
       return isActive;
   }

   @Override
   public boolean isCredentialsNonExpired() {
       return isActive;
   }

   @Override
   public boolean isEnabled() {
       return isActive;
   }

   private Collection<? extends GrantedAuthority> mapRolesToAuthorities() {
       return appUserRoleControl != null ? Collections.singleton(new SimpleGrantedAuthority(appUserRoleControl.getAppUserRole().getRoleName()))
           : Collections.emptyList();
   }
}
