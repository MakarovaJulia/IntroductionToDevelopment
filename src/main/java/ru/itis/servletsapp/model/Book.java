package ru.itis.servletsapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String description;
    private Long authorId;
    Timestamp createdAt;
    private Long coverId;
    private Boolean isPublished;
    private List<Chapter> chapters;
}