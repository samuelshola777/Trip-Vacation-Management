package com.trip_excursion_management.appUser.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.appUser.data.models.GroupMember;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    boolean existsByAppUserAndGroup(Long appUserId, Long groupId);
    GroupMember findByAppUserAndGroup(Long appUserId, Long groupId);
    void deleteByAppUserAndGroup(Long appUserId, Long groupId);
    void deleteAllByGroup(Long groupId);
    List<GroupMember> findAllByGroup(Long groupId);
}

