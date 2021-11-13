package ru.itis.servletsapp.services;

import java.util.List;

public interface UsersBooksService {
    void add(Long user_id, Long book_id);
    List<Long> findByUserId(Long userId);
}
