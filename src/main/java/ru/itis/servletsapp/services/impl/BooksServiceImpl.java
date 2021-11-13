package ru.itis.servletsapp.services.impl;

import ru.itis.servletsapp.dao.BooksRepository;
import ru.itis.servletsapp.dto.out.BookDto;
import ru.itis.servletsapp.model.Book;
import ru.itis.servletsapp.services.BooksService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BooksServiceImpl implements BooksService {
    private final BooksRepository booksRepository;

    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public List<BookDto> getByAuthorId(Long authorId) {
        return booksRepository.findByAuthorId(authorId).stream()
                .map(BookDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getAll(){
        return booksRepository.findAll().stream()
                .map(BookDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDto> getById(Long id) {
        return booksRepository.findById(id).map(BookDto::from);
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        Book book = Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .isPublished(bookDto.getIsPublished())
                .authorId(bookDto.getAuthorId())
                .createdAt(bookDto.getCreatedAt())
                .build();

        Book savedBook = booksRepository.save(book);
        return BookDto.from(savedBook);
    }
}
