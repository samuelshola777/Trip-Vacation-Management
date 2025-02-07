package com.trip_excursion_management.vacation.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mediaUrl;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_id", nullable = true)

    private Vacation vacation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_comments_id", nullable = true)
    private VacationComments vacationComments;
    private int rating;

}
