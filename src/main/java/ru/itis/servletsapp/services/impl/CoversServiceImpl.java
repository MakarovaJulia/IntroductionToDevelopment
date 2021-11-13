package ru.itis.servletsapp.services.impl;

import ru.itis.servletsapp.dao.BooksRepository;
import ru.itis.servletsapp.dao.FilesRepository;
import ru.itis.servletsapp.dto.out.BookDto;
import ru.itis.servletsapp.exceptions.NotFoundException;
import ru.itis.servletsapp.model.FileInfo;
import ru.itis.servletsapp.services.CoversService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class CoversServiceImpl implements CoversService {

    String path = "/Users/Julia/uploaded_files";

    private final FilesRepository filesRepository;
    private final BooksRepository booksRepository;

    public CoversServiceImpl(FilesRepository filesRepository, BooksRepository booksRepository) {
        this.filesRepository = filesRepository;
        this.booksRepository = booksRepository;
    }

    @Override
    public FileInfo saveFileToStorage(BookDto book, InputStream inputStream, String originalFileName, String contentType, Long size) {
        FileInfo fileInfo = new FileInfo(
                null,
                originalFileName,
                UUID.randomUUID().toString(),
                size,
                contentType
        );
        try {
            Files.copy(inputStream, Paths.get(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileInfo = filesRepository.save(fileInfo);
            booksRepository.updateCoverForBook(book.getId(), fileInfo.getId());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return fileInfo;
    }

    @Override
    public void readFileFromStorage(Long fileId, OutputStream outputStream) {
        Optional<FileInfo> optionalFileInfo = filesRepository.findById(fileId);
        FileInfo fileInfo = optionalFileInfo.orElseThrow(() -> new NotFoundException("File not found"));

        File file = new File(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FileInfo getFileInfo(Long fileId) {
        return filesRepository.findById(fileId).orElseThrow(() -> new NotFoundException("File not found"));
    }
}