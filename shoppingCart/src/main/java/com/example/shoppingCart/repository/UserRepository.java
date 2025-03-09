package com.example.shoppingCart.repository;

import com.example.shoppingCart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE (:email IS NULL OR email = :email) " +
            "AND (:phoneNumber IS NULL OR phone_number = :phoneNumber)", nativeQuery = true)
    List<User> findByEmailOrPhoneNumber(@Param("email") String email, @Param("phoneNumber") String phoneNumber);
}
