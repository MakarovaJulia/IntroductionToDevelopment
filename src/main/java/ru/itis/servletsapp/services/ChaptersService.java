package ru.itis.servletsapp.services;

import ru.itis.servletsapp.dto.out.ChapterDto;

import java.util.List;
import java.util.Optional;

public interface ChaptersService {
    List<ChapterDto> getByBookId(Long bookId);
    Optional<ChapterDto> getById(Long id);
    ChapterDto addChapter(ChapterDto bookDto);
}
