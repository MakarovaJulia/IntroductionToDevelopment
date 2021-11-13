package ru.itis.servletsapp.services;

import ru.itis.servletsapp.dto.out.CommentDto;

import java.util.List;

public interface CommentsService {
    List<CommentDto> getByAuthorId(Long authorId);
    List<CommentDto> getAll();
    CommentDto addComment(CommentDto commentDto);
}