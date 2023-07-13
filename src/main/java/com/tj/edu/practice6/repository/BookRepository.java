package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
