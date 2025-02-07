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
import java.util.List;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class VacationDisplayMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mediaUrl;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_id", nullable = true)
    private Vacation vacation;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VacationComments> vacationMediaComments;
    private int rating;
    @Column(name = "created_at")
    private LocalDateTime createdAt;



}


