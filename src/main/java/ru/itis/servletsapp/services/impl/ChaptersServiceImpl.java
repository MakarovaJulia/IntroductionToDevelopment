package ru.itis.servletsapp.services.impl;

import ru.itis.servletsapp.dao.ChaptersRepository;
import ru.itis.servletsapp.dto.out.ChapterDto;
import ru.itis.servletsapp.model.Chapter;
import ru.itis.servletsapp.services.ChaptersService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChaptersServiceImpl implements ChaptersService {
    private final ChaptersRepository chaptersRepository;

    public ChaptersServiceImpl(ChaptersRepository chaptersRepository) {
        this.chaptersRepository = chaptersRepository;
    }


    @Override
    public List<ChapterDto> getByBookId(Long bookId) {
        return chaptersRepository.findByBookId(bookId).stream()
                .map(ChapterDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChapterDto> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public ChapterDto addChapter(ChapterDto chapterDto) {
        Chapter chapter = Chapter.builder()
                .id(chapterDto.getId())
                .bookId(chapterDto.getBookId())
                .number(chapterDto.getNumber())
                .title(chapterDto.getTitle())
                .createdAt(chapterDto.getCreatedAt())
                .content(chapterDto.getContent())
                .build();

        Chapter savedChapter = chaptersRepository.save(chapter);
        return ChapterDto.from(savedChapter);
    }
}