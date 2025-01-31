package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto save(Schedule schedule);

    List<ScheduleResponseDto> findAll(String updatedAt, String name);

    Optional<Schedule> findById(Long id);

    Schedule update(Long id, String name, String title, String contents);

    void delete(Schedule schedule);

}
