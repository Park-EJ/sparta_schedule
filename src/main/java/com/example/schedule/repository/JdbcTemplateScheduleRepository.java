package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto save(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", schedule.getName());
        parameters.put("password", schedule.getPassword());
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());
        LocalDateTime createdAt = LocalDateTime.now();
        parameters.put("createdAt", createdAt);
        LocalDateTime updatedAt = LocalDateTime.now();
        parameters.put("updatedAt", updatedAt);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getName(), schedule.getTitle(), schedule.getContents(), createdAt, updatedAt);
    }

    @Override
    public List<ScheduleResponseDto> findAll(String updatedAt, String name) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedule WHERE 1 = 1");
        List<Object> params = new ArrayList<>();


        if (updatedAt != null && !updatedAt.isEmpty()) {
            sql.append(" AND DATE(updatedAt) = ?");
            params.add(LocalDate.parse(updatedAt));
        }


        if (name != null && !name.isEmpty()) {
            sql.append(" AND name = ?");
            params.add(name);
        }

        sql.append(" ORDER BY updatedAt DESC");

        return jdbcTemplate.query(
                sql.toString(),
                params.toArray(),
                (rs, rowNum) -> new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime()
                )
        );
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        List<Schedule> schedules = jdbcTemplate.query(
                "select * from schedule where id = ?",
                (rs, rowNum) -> new Schedule(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime()),
                id
        );

        return schedules.stream().findAny();
    }

    @Override
    public Schedule update(Long id, String name, String title, String contents) {
        LocalDateTime updatedAt = LocalDateTime.now();

        jdbcTemplate.update(
                "update schedule set name = ?, title = ?, contents = ?, updatedAt = ? WHERE id = ?",
                name,
                title,
                contents,
                updatedAt,
                id
        );

        return jdbcTemplate.queryForObject(
                "select * from schedule where id = ?",
                (rs, rowNum) -> new Schedule(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime()),
//                        rs.getTimestamp("updatedAt") != null ? rs.getTimestamp("updatedAt").toLocalDateTime() : null
                id
        );
    }

    @Override
    public void delete(Schedule schedule) {
        jdbcTemplate.update(
                "delete from schedule where id = ?",
                schedule.getId()
        );
    }

}