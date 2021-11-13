package ru.itis.servletsapp.services.impl;

import ru.itis.servletsapp.dao.FilesRepository;
import ru.itis.servletsapp.exceptions.NotFoundException;
import ru.itis.servletsapp.model.FileInfo;
import ru.itis.servletsapp.services.FilesService;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Optional;

public class FilesServiceImpl implements FilesService {
    private final String path;
    private final FilesRepository filesRepository;

    public FilesServiceImpl(String path, FilesRepository filesRepository) {
        this.path = path;
        this.filesRepository = filesRepository;
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
