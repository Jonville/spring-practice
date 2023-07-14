package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Book;
import com.tj.edu.practice6.model.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;


//    @Test
//    void crudTest1() {
//        BookReviewInfo bookReviewInfo = BookReviewInfo.builder()
//                .bookId(1L)
//                .avgReviewScore(2.3f)
//                .reviewCount(8)
//                .build();
//
//        bookReviewInfoRepository.save(bookReviewInfo);
//
//    }
//    @Test
//    void crudTest2() {
//        Book book = getGivenBook();
//        BookReviewInfo bookReviewInfo = getGivenBookReviewInfo(book);
//
//        List<BookReviewInfo> bookReviewInfoList = bookReviewInfoRepository.findAll();
//        bookReviewInfoList.forEach(System.out::println);
//
////        Book book2 = bookRepository.findById(bookReviewInfoList.get(0).getBookId()).orElseThrow(RuntimeException::new);
////        System.out.println(">>> book2: " + book2);
//    }

    @Test
    void oneToOneTest1() {
        Book book = getGivenBook();
        BookReviewInfo bookReviewInfo = getGivenBookReviewInfo(book);

        bookReviewInfoRepository.findById(1L);

    }

    private Book getGivenBook() {
        Book book = Book.builder()
                .name("아주 쉬운 스프링 부트3.1 !")
                .author("저자1")
                .build();
        return bookRepository.save(book);
    }

    private BookReviewInfo getGivenBookReviewInfo(Book book) {
//        Book book = getGivenBook();

        BookReviewInfo bookReviewInfo = BookReviewInfo.builder()
                .book(book)
                .avgReviewScore(2.3f)
                .reviewCount(8)
                .build();
        return bookReviewInfoRepository.save(bookReviewInfo);
    }

}