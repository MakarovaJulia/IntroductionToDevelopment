package ru.itis.servletsapp.services;

import ru.itis.servletsapp.dto.out.BookDto;
import ru.itis.servletsapp.model.FileInfo;

import java.io.InputStream;

public interface CoversService extends FilesService{
    FileInfo saveFileToStorage(BookDto book, InputStream file, String originalFileName, String contentType, Long size);
}
