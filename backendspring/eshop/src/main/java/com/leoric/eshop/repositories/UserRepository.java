package com.leoric.eshop.repositories;

import com.leoric.eshop.models.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<LocalUser, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    Optional<LocalUser> findByUsername(String username);
}
