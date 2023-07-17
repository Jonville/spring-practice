package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Book;
import com.tj.edu.practice6.model.BookAndId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);

    // JPQL
    @Query(value = "select b from Book b where name = ?1")
    List<Book> findByMyBooks(String name);

    @Query(value = "select b from Book b where name = ?2 and id = ?1")
    List<Book> findByMyBooksAndMyId(Long id, String name);

    @Query(value = "select b from Book b where name like %:name% and id = :id")
    List<Book> findByNamedByMyBooksAndMyId(@Param("id") Long id, @Param("name") String name);

    @Query(value = "select b.name from Book b where name = ?1")
    List<String> findNameByMyBooks(String name);

    // return 해주는 book객체를 convert가 안되서 안되는 메소드
    @Query(value = "select b.id id, b.name name from Book b where name = ?1")
//    List<Book> findNameIdByMyBooks(String name);
    List<Map<String, Object>> findNameIdByMyBooks(String name);

    @Query(value = "select b.id id, b.name name from Book b where name = :name")
    List<Map<String, Object>> findByNamedNameIdByMyBooks(@Param("name") String name);

    @Query(value = "select b.id abc, b.name name2 from Book b where name = :name")
    List<BookAndId> findByCustomNamedNameIdByMyBooks(@Param("name") String name);

    // native query

    @Query(value = "select * from book where name = ?1" , nativeQuery = true)
    List<Book> findByNativeMyBooks(String name);

    @Query(value = "select * from book where name = :name1" , nativeQuery = true)
    List<Book> findByNativeNameMyBooks(@Param("name1") String name);

//    @Query(value = """
//                select 쿼리문 .....
//                ....
//                ...
//                ...
//                ...
//                .
//
//            """ , nativeQuery = true)
//    List<Book> findByNativeName2(String 검색할변수명);

    List<Book> findByNameIsNullAndNameEqualsAndCreateAtGreaterThanEqualAndUpdateAtLessThan(String name, LocalDateTime createAt, LocalDateTime updateAt);
}
