package com.tj.edu.practice5.jpa.repository;

import com.tj.edu.practice5.jpa.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser , String> {

}
