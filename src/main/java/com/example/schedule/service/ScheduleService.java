package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto save(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAll(String updatedAt, String name);

    ScheduleResponseDto findById(Long id);

    ScheduleResponseDto update(Long id, String name, String password, String title, String contents);

    void delete(Long id, String password);

}
