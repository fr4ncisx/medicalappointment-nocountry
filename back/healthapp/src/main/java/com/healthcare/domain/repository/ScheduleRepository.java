package com.healthcare.domain.repository;

import com.healthcare.domain.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsByMedicIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            Long medicId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);
}