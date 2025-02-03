package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    @Setter
    private Long id;
    private String name;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Schedule(String name, String password, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Schedule(String name, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Schedule(String name, String password, String title, String contents) {
        this.name = name;
        this.password = password;
        this.title = title;
        this.contents = contents;
    }

    public Schedule(long id, String name, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}