package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Author;
import com.tj.edu.practice6.model.Book;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    void manyToManyTest() {
        Book book1 = givenBook("React 책");
        Book book2 = givenBook("Spring boot 책");
        Book book3 = givenBook("Spring Data JPA 책");
        Book book4 = givenBook("React Native 책");

        Author author1 = givenAuthor("박기사");
        Author author2 = givenAuthor("이제목");

        book1.setAuthors(Lists.newArrayList(author1));
        book2.setAuthors(Lists.newArrayList(author2));
        book3.setAuthors(Lists.newArrayList(author1, author2));
        book3.setAuthors(Lists.newArrayList(author1, author2));

        author1.setBooks(Lists.newArrayList(book1, book2, book3));
        author2.setBooks(Lists.newArrayList(book2, book3, book4));

        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
        authorRepository.saveAll(Lists.newArrayList(author1 , author2));

//        bookRepository.findAll().forEach(System.out::println);

        System.out.println(bookRepository.findAll().get(2).getAuthors());   // join 쿼리
        System.out.println(authorRepository.findAll().get(1).getBooks());   // join 쿼리

    }

    private Book givenBook(String name) {
        Book book = Book.builder()
                .name(name)
                .build();
        return bookRepository.save(book);
    }
    private Author givenAuthor(String name) {
        Author author = Author.builder()
                .name(name)
                .build();
        return authorRepository.save(author);
    }

}