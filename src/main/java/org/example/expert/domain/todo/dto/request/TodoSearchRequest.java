package org.example.expert.domain.todo.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoSearchRequest {
    private String weather;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
