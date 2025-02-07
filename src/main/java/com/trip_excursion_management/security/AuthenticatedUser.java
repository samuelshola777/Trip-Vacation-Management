package com.trip_excursion_management.security;

import com.trip_excursion_management.appUser.data.models.AppUserRole;
import com.trip_excursion_management.appUser.data.models.AppUser;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AuthenticatedUser implements UserDetails {

    private AppUser appUser;

    private List<AppUserRole> roles;

    public String getUsername() {
        return appUser.getEmail();
    }


    public String getPassword() {
        return appUser.getPassword();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapRolesToAuthorities();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return appUser.isActive();
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities() {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

}
