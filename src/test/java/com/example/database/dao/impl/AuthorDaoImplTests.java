package com.example.database.dao.impl;

import com.example.database.TestDataUtil;
import com.example.database.domain.Author;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplat;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author= TestDataUtil.createAuthorTestA();
        underTest.create(author);

        verify(jdbcTemplat).update(
                eq("INSERT INTO authors (id, name, age) values (?, ?, ?)"),
                eq(1L),
                eq("Khanh"),
                eq(21)
        );
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql(){
        underTest.findOne(1L);
        verify(jdbcTemplat).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindManyGeneratesTheCorrectSql(){
        underTest.find();
        verify(jdbcTemplat).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesTheCorrectSql(){
        Author author=TestDataUtil.createAuthorTestA();
        underTest.update(1L,author);
        verify(jdbcTemplat).update(
                eq("UPDATE authors SET id= ?,name= ?,age= ? WHERE id=?"),
                eq(author.getId()),
                eq(author.getName()),
                eq(author.getAge()),
                eq(1L)
        );
    }

    @Test
    public void testThatDeleteGeneratesTheCorrectaSql(){
        underTest.delete(1L);

        verify(jdbcTemplat).update(
                eq("DELETE FROM authors WHERE id= ?"),
                eq(1L)
        );
    }

}
