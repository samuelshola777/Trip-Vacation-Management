package com.trip_excursion_management.security;

import com.trip_excursion_management.appUser.data.models.AppUser;
import com.trip_excursion_management.appUser.data.models.AppUserRole;
import com.trip_excursion_management.appUser.data.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.trip_excursion_management.appUser.data.repository.AppUserRoleRepository;

@Service
@RequiredArgsConstructor

public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser =
                appUserRepository.findByEmailEqualsIgnoreCase(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));


        return appUser;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<AppUserRole> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

    }
}
