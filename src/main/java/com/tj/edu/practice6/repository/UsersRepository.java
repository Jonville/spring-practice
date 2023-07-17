package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users , Long> {
    List<Users> findByName(String name);
}
