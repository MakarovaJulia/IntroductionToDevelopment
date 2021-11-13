package ru.itis.servletsapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Chapter {
    private Long id;
    private Long bookId;
    private Long number;
    private String title;
    Timestamp createdAt;
    private String content;
}