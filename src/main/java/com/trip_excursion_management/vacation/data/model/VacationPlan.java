package com.trip_excursion_management.vacation.data.model;

import org.springframework.data.annotation.Id;

import com.trip_excursion_management.appUser.data.models.Group;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacationPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_id", nullable = true)
    private Vacation vacation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_comments_id", nullable = true)
    private VacationComments vacationComments;
    private int rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_display_media_id", nullable = true)
    private VacationDisplayMedia vacationDisplayMedia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_history_id", nullable = true)
    private VacationHistory vacationHistory;
    private boolean isDeleted;
    private LocalDateTime createdAt;
}
