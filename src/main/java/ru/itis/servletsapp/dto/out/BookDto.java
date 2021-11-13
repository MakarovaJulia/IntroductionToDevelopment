package ru.itis.servletsapp.dto.out;

import lombok.*;
import ru.itis.servletsapp.model.Book;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private Boolean isPublished;
    private Long authorId;
    Timestamp createdAt;
    private Long coverId;

    public static BookDto from(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .isPublished(book.getIsPublished())
                .authorId(book.getAuthorId())
                .createdAt(book.getCreatedAt())
                .coverId(book.getCoverId())
                .build();
    }
}