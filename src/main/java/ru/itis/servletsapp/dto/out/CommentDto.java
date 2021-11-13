package ru.itis.servletsapp.dto.out;

import lombok.*;
import ru.itis.servletsapp.model.Comment;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private UserDto author;
    private String content;
    private Timestamp createdAt;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .author(UserDto.from(comment.getAuthor()))
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}