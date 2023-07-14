package com.tj.edu.practice6.repository;

import com.tj.edu.practice6.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , Long> {

}
