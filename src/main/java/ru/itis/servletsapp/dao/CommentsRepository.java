package ru.itis.servletsapp.dao;

import ru.itis.servletsapp.dao.base.CrudRepository;
import ru.itis.servletsapp.model.Comment;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByAuthorId(Long authorId);
}