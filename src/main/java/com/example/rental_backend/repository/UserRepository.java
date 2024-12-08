package com.example.rental_backend.repository;

import com.example.rental_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for performing database operations on the User entity.
 * Extends JpaRepository to provide CRUD and custom query methods.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     * @param email the email address to search for
     * @return an Optional containing the found User, or empty if no user is found
     */
    Optional<User> findByEmail(String email);
}
