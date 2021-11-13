package ru.itis.servletsapp.services.impl;

import ru.itis.servletsapp.dao.UsersBooksRepository;
import ru.itis.servletsapp.services.UsersBooksService;

import java.util.List;

public class UsersBooksServiceImpl implements UsersBooksService {
    private final UsersBooksRepository usersBooksRepository;

    public UsersBooksServiceImpl(UsersBooksRepository usersBooksRepository) {
        this.usersBooksRepository = usersBooksRepository;
    }

    @Override
    public void add(Long user_id, Long book_id) {
        usersBooksRepository.add(user_id, book_id);
    }

    @Override
    public List<Long> findByUserId(Long userId) {
        return usersBooksRepository.findByUserId(userId);
    }
}
