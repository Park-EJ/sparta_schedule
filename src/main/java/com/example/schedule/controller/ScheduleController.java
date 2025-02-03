package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.save(requestDto));
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(
            @RequestParam(required = false) String updatedAt,
            @RequestParam(required = false) String name) {

        return ResponseEntity.ok(scheduleService.findAll(updatedAt, name));
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    // 선택 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.update(id, requestDto.getName(), requestDto.getPassword(), requestDto.getTitle(), requestDto.getContents()));
    }

    // 선택 일정 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.delete(id, requestDto.getPassword());
    }

}