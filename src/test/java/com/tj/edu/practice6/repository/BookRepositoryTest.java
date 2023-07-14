package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest() {
        Book book = Book.builder()
                .name("아주 쉬운 스프링 부트3.1 !")
                .author("저자1")
                .build();

        bookRepository.save(book);
    }

}