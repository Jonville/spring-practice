package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review , Long> {

}
