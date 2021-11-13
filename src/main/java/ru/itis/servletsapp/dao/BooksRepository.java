package ru.itis.servletsapp.dao;

import ru.itis.servletsapp.dao.base.CrudRepository;
import ru.itis.servletsapp.model.Book;

import java.util.List;

public interface BooksRepository extends CrudRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);
    void updateCoverForBook(Long userId, Long fileId);
}
