package ru.itis.servletsapp.services;

import ru.itis.servletsapp.model.FileInfo;

import java.io.OutputStream;

public interface FilesService {
    void readFileFromStorage(Long fileId, OutputStream outputStream);
    FileInfo getFileInfo(Long fileId);
}
