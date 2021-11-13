package ru.itis.servletsapp.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.servletsapp.dao.ChaptersRepository;
import ru.itis.servletsapp.model.Chapter;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class ChaptersRepositoryImpl implements ChaptersRepository {

    private final static String SQL_INSERT = "insert into chapters(book_id, number, created_at, title, content) " +
            "values (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "update chapters set number = ?, book_id = ?, created_at = ?, title = ?, content = ? where id = ?";
    private final static String SQL_SELECT_BY_ID = "select * from chapters left join books on chapters.book_id = books.id where chapters.id = ?";
    private final static String SQL_SELECT_ALL = "select * from chapters left join books on chapters.book_id = books.id";
    private final static String SQL_SELECT_BY_BOOK_ID = "select * from chapters left join books on chapters.book_id = books.id where books.id = ?";

    private final RowMapper<Chapter> rowMapper = (row, rowNumber) ->Chapter.builder()
            .id(row.getLong("id"))
            .number(row.getLong("number"))
            .createdAt(row.getTimestamp("created_at"))
            .title(row.getString("title"))
            .bookId(row.getLong("book_id"))
            .content(row.getString("content"))
            .build();

    private final JdbcTemplate jdbcTemplate;

    public ChaptersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Chapter> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Chapter> findByBookId(Long bookId) {
            return jdbcTemplate.query(SQL_SELECT_BY_BOOK_ID, rowMapper, bookId);
    }

    @Override
    public List<Chapter> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public Chapter save(Chapter item) {
        if(item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setLong(1, item.getBookId());
                statement.setLong(2, item.getNumber());
                statement.setTimestamp(3, item.getCreatedAt());
                statement.setString(4, item.getTitle());
                statement.setString(5, item.getContent());
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setId(keyHolder.getKey().longValue());
            }
        } else {
            jdbcTemplate.update(SQL_UPDATE,
                    item.getNumber(),
                    item.getBookId(),
                    item.getCreatedAt(),
                    item.getTitle(),
                    item.getContent(),
                    item.getId()
            );
        }
        return item;
    }

    @Override
    public void delete(Long id) {}
}