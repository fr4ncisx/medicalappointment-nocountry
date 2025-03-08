package com.healthcare.domain.model.entity;

import com.healthcare.domain.dto.request.ScheduleRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime start;
    @Column(nullable = false)
    private LocalDateTime end;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;
    @ManyToOne
    @JoinColumn(name = "medic_id", nullable = false)
    private Medic medic;

    public Schedule(ScheduleRequestDTO scheduleRequestDTO, Medic medic) {
        this.start = scheduleRequestDTO.getStart();
        this.end = scheduleRequestDTO.getEnd();
        this.dayOfWeek = scheduleRequestDTO.getDayOfWeek();
        this.medic = medic;
    }

    public Schedule(ScheduleRequestDTO scheduleRequestDTO) {
        this.start = scheduleRequestDTO.getStart();
        this.end = scheduleRequestDTO.getEnd();
        this.dayOfWeek = scheduleRequestDTO.getDayOfWeek();
    }
}