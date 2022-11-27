package com.tpe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpe.domain.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

	@EntityGraph(attributePaths = "roles")
	Optional<MyUser> findByUserName(String userName);
	
}
