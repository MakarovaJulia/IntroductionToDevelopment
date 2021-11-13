package ru.itis.servletsapp.dto.out;

import lombok.*;
import ru.itis.servletsapp.model.Chapter;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDto {
    private Long id;
    private Long bookId;
    private Long number;
    private String title;
    Timestamp createdAt;
    private String content;

    public static ChapterDto from(Chapter chapter) {
        return ChapterDto.builder()
                .id(chapter.getId())
                .bookId(chapter.getBookId())
                .number(chapter.getNumber())
                .title(chapter.getTitle())
                .createdAt(chapter.getCreatedAt())
                .content(chapter.getContent())
                .build();
    }
}