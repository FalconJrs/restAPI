package com.example.myrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myrestapi.entity.User;

//JpaRepository<Table_name, PK_type>
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(Long userId);
	
}
