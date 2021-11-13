package ru.itis.servletsapp.services.impl;

import ru.itis.servletsapp.dao.CommentsRepository;
import ru.itis.servletsapp.dto.out.CommentDto;
import ru.itis.servletsapp.model.Comment;
import ru.itis.servletsapp.model.User;
import ru.itis.servletsapp.services.CommentsService;

import java.util.List;
import java.util.stream.Collectors;

public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepository commentsRepository;

    public CommentsServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public List<CommentDto> getByAuthorId(Long authorId) {
        return commentsRepository.findByAuthorId(authorId).stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getAll() {
        return commentsRepository.findAll().stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto addComment(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .id(commentDto.getId())
                .author(User.builder()
                        .id(commentDto.getAuthor().getId())
                        .avatarId(commentDto.getAuthor().getAvatarId())
                        .email(commentDto.getAuthor().getEmail())
                        .firstName(commentDto.getAuthor().getFirstName())
                        .lastName(commentDto.getAuthor().getLastName())
                        .build())
                .content(commentDto.getContent())
                .createdAt(commentDto.getCreatedAt())
                .build();
        Comment savedComment = commentsRepository.save(comment);
        return CommentDto.from(savedComment);
    }
}