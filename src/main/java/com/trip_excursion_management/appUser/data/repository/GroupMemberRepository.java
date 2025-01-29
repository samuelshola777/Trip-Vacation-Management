package com.trip_excursion_management.appUser.data.repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip_excursion_management.appUser.data.models.GroupMember;

public interface GroupMemberRepository extends JpaRepository<GroupMember, UUID> {
    boolean existsByAppUserAndGroup(UUID appUserId, UUID groupId);
    Optional<GroupMember> findByAppUserAndGroup(UUID appUserId, UUID groupId);
    void deleteByAppUserAndGroup(UUID appUserId, UUID groupId);
    void deleteAllByGroup(UUID groupId);
    List<GroupMember> findAllByGroup(UUID groupId);
}
