package org.example.expert.domain.todo.dto.response;

import lombok.Getter;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

@Getter
public class TodoResponse {

    private final Long id;
    private final String title;
    private final String contents;
    private final String weather;
    private final UserResponse user;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public TodoResponse(Long id, String title, String contents, String weather, UserResponse user, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.weather = weather;
        this.user = user;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public interface TodoRepository extends JpaRepository<Todo, Long> {

        @Query("SELECT t FROM Todo t WHERE (:weather IS NULL OR t.weather = :weather)")
        Page<Todo> findByWeather(@Param("weather") String weather, Pageable pageable);

        @Query("SELECT t FROM Todo t WHERE " +
                "(:start IS NULL OR t.modifiedAt >= :start) AND " +
                "(:end IS NULL OR t.modifiedAt <= :end)")
        Page<Todo> findByDateRange(@Param("start") LocalDateTime start,
                                   @Param("end") LocalDateTime end,
                                   Pageable pageable);

        @Query("SELECT t FROM Todo t WHERE " +
                "(:weather IS NULL OR t.weather = :weather) AND " +
                "(:start IS NULL OR t.modifiedAt >= :start) AND " +
                "(:end IS NULL OR t.modifiedAt <= :end)")
        Page<Todo> searchTodos(@Param("weather") String weather,
                               @Param("start") LocalDateTime start,
                               @Param("end") LocalDateTime end,
                               Pageable pageable);
    }

}
