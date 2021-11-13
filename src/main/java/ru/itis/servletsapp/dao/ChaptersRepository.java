package ru.itis.servletsapp.dao;

import ru.itis.servletsapp.dao.base.CrudRepository;
import ru.itis.servletsapp.model.Chapter;

import java.util.List;

public interface ChaptersRepository  extends CrudRepository<Chapter, Long> {
    List<Chapter> findByBookId(Long authorId);
}
