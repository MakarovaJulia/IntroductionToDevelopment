package ru.itis.servletsapp.services;

import ru.itis.servletsapp.dto.out.UserDto;
import ru.itis.servletsapp.model.FileInfo;

import java.io.InputStream;

public interface UserPhotosService extends FilesService{
    FileInfo saveFileToStorage(UserDto user, InputStream file, String originalFileName, String contentType, Long size);
}
