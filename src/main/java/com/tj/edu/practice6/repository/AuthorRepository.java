package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author , Long> {

}
