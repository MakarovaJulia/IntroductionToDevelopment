package ru.itis.servletsapp.services;

import ru.itis.servletsapp.dto.out.BookDto;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    List<BookDto> getByAuthorId(Long authorId);
    List<BookDto> getAll();
    Optional<BookDto> getById(Long id);
    BookDto addBook(BookDto bookDto);
}