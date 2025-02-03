package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    @Override
    public ScheduleResponseDto save(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto.getName(), requestDto.getPassword(), requestDto.getTitle(), requestDto.getContents());

        return scheduleRepository.save(schedule);
    }

    @Transactional
    @Override
    public List<ScheduleResponseDto> findAll(String updatedAt, String name) {
        List<ScheduleResponseDto> schedules = scheduleRepository.findAll(updatedAt, name).stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getName(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                ))
                .collect(Collectors.toList());

        if (schedules.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.");
        }

        return schedules;
    }

    @Transactional
    @Override
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.")
        );

        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto update(Long id, String name, String password, String title, String contents) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.")
        );

        if (!schedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "비밀번호를 잘못 입력하였습니다.");
        }

        Schedule updatedSchedule = scheduleRepository.update(schedule.getId(), name, title, contents);

        return new ScheduleResponseDto(updatedSchedule.getId(), updatedSchedule.getName(), updatedSchedule.getTitle(), updatedSchedule.getContents(), updatedSchedule.getCreatedAt(), updatedSchedule.getUpdatedAt());
    }

    @Transactional
    @Override
    public void delete(Long id, String password) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.")
        );

        if (!schedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "비밀번호를 잘못 입력하였습니다.");
        }

        scheduleRepository.delete(schedule);
    }

}