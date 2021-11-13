package ru.itis.servletsapp.dao;

import java.util.List;

public interface UsersBooksRepository {
    void add(Long user_id, Long book_id);
    List<Long> findByUserId(Long userId);
}
