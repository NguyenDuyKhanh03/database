package com.example.database.dao.impl;

import com.example.database.dao.BookDao;
import com.example.database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId()
        );
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> result=jdbcTemplate.query(
                "SELECT isbn, title, author_id from books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn
        );
        return result.stream().findFirst();
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books",
                new BookRowMapper()
        );
    }

    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update(
                "UPDATE books SET isbn= ?,title= ?,author_id= ? WHERE isbn= ?",
                book.getIsbn(),book.getTitle(),book.getAuthorId(),isbn
        );
    }

    @Override
    public void delete(String isBn) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE isbn= ?",
                isBn
        );
    }

    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }
}
