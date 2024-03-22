package com.example.database;

import com.example.database.domain.Author;
import com.example.database.domain.Book;

public class TestDataUtil {
    public TestDataUtil() {
    }

    public static Author createAuthorTestA() {
        return Author.builder()
                .id(1L)
                .name("Khanh")
                .age(21)
                .build();
    }
    public static Author createAuthorTestB() {
        return Author.builder()
                .id(2L)
                .name("Khanh2")
                .age(21)
                .build();
    }
    public static Author createAuthorTestC() {
        return Author.builder()
                .id(3L)
                .name("Khanh3")
                .age(21)
                .build();
    }


    public static Book createBookTest() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }
    public static Book createBookTestA() {
        return Book.builder()
                .isbn("978-1-2345-6789-1")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }
    public static Book createBookTestB() {
        return Book.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }
}
