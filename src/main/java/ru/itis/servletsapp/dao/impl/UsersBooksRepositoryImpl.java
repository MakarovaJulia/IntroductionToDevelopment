package ru.itis.servletsapp.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.servletsapp.dao.UsersBooksRepository;

import javax.sql.DataSource;
import java.util.List;

public class UsersBooksRepositoryImpl implements UsersBooksRepository {
    private final static String SQL_INSERT = "insert into users_books(user_id, book_id)" +
            "values (?, ?)";
    private final static String SQL_SELECT_BY_USER = "select book_id from users_books where users_books.user_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public UsersBooksRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Long user_id, Long book_id) {
        jdbcTemplate.update(SQL_INSERT, user_id, book_id);
    }

    @Override
    public List<Long> findByUserId(Long userId) {
       return jdbcTemplate.queryForList(SQL_SELECT_BY_USER, Long.class, userId);
    }
}
