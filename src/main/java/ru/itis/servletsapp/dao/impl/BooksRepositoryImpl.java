package ru.itis.servletsapp.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.servletsapp.dao.BooksRepository;
import ru.itis.servletsapp.model.Book;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class BooksRepositoryImpl implements BooksRepository {

    private final static String SQL_INSERT = "insert into books(author_id, created_at, title, description, cover_id, is_published) " +
            "values (?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "update books set author_id = ?, created_at = ?, title = ?, description = ?, cover_id= ?, is_published = ?  where id = ?";
    private final static String SQL_UPDATE_COVER = "update books set cover_id = ? where id = ?";
    private final static String SQL_SELECT_BY_ID = "select * from books left join users on books.author_id = users.id where books.id = ?";
    private final static String SQL_SELECT_ALL = "select * from books left join users on books.author_id = users.id";
    private final static String SQL_SELECT_BY_AUTHOR_ID = "select * from books left join users on books.author_id = users.id where users.id = ?";

    private final RowMapper<Book> rowMapper = (row, rowNumber) ->Book.builder()
            .id(row.getLong("id"))
            .title(row.getString("title"))
            .description(row.getString("description"))
            .isPublished(row.getBoolean("is_published"))
            .authorId(row.getLong("author_id"))
            .coverId(row.getLong("cover_id") == 0 ? null : row.getLong("cover_id"))
            .createdAt(row.getTimestamp("created_at"))
            .build();

    private final JdbcTemplate jdbcTemplate;

    public BooksRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Book> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateCoverForBook(Long bookId, Long fileId) {
        jdbcTemplate.update(SQL_UPDATE_COVER,
                fileId, bookId
        );
    }

    @Override
    public List<Book> findByAuthorId(Long authorId) {
        return jdbcTemplate.query(SQL_SELECT_BY_AUTHOR_ID, rowMapper, authorId);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public Book save(Book item) {
        if(item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setLong(1, item.getAuthorId());
                statement.setTimestamp(2, item.getCreatedAt());
                statement.setString(3, item.getTitle());
                statement.setString(4, item.getDescription());
                statement.setLong(5, item.getCoverId());
                statement.setBoolean(6, item.getIsPublished() == null ? true : false);
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setId(keyHolder.getKey().longValue());
            }
        } else {
            jdbcTemplate.update(SQL_UPDATE,
                    item.getAuthorId(),
                    item.getCreatedAt(),
                    item.getTitle(),
                    item.getDescription(),
                    item.getCoverId(),
                    item.getIsPublished(),
                    item.getId()
            );
        }
        return item;
    }

    @Override
    public void delete(Long id) {}
}