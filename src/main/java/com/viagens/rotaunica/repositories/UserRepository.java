package com.viagens.rotaunica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viagens.rotaunica.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	boolean existByEmail(String email);
}
