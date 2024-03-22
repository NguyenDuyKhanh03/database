package com.example.database.dao.impl;

import com.example.database.TestDataUtil;
import com.example.database.domain.Author;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTests {

    AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author= TestDataUtil.createAuthorTestA();
        underTest.create(author);
        Optional<Author> result= underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorMultipleCanBeCreatedAndRecalled(){
        Author authorA= TestDataUtil.createAuthorTestA();
        underTest.create(authorA);
        Author authorB= TestDataUtil.createAuthorTestB();
        underTest.create(authorB);
        Author authorC= TestDataUtil.createAuthorTestC();
        underTest.create(authorC);
        List<Author> result= underTest.find();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(authorA,authorB,authorC);

    }

    @Test
    public void testThatAuthorCanBeUpdated(){
        Author author=TestDataUtil.createAuthorTestA();
        underTest.create(author);
        author.setAge(22);
        underTest.update(1L,author);
        Optional<Author> result=underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }
    @Test
    public void testThatAuthorCanBeDeleted(){
        Author author=TestDataUtil.createAuthorTestA();
        underTest.create(author);
        underTest.delete(author.getId());
        Optional<Author> result= underTest.findOne(author.getId());
        assertThat(result).isEmpty();
    }

}
