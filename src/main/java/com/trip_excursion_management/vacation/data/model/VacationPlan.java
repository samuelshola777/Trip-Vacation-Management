package com.trip_excursion_management.vacation.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.persistence.OneToOne;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import com.trip_excursion_management.appUser.data.models.Group;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacationPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // just a name of the vacation more like celebrate esther on a trip
    private String name;
    // a description of the vacation plan
    private String description;
    // the day the day the customer will like to go for the vacation ride

    private LocalDate startDate;
    // after the admin receives the request the admin sets the end of the vacation, more like  if the customer didn't show up in this period of time the admin will cancel the vacation trip
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private VacationPlanStatus vacationPlanStatus;

    // the group that the vacation plan is for
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_id", nullable = true)
    private Vacation vacation;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_comments_id", nullable = true)
    private Set<VacationComments> vacationComments;

    private int rating;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_display_media_id", nullable = true)
    private Set<VacationDisplayMedia> vacationDisplayMedia;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_history_id", nullable = true)
    private Set<VacationHistory> vacationHistory;

    private boolean isDeleted;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
