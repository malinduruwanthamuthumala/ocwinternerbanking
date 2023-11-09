package com.occw.occwinternetbanking.jpa.repository;
import com.occw.occwinternetbanking.jpa.components.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

	@Query("SELECT c FROM Customer c WHERE c.email = :email")
	List<Customer> findByEmail(@Param("email") String email);
}
