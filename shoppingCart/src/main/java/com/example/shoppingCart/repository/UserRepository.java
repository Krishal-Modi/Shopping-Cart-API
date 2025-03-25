package com.example.shoppingCart.repository;

import com.example.shoppingCart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users " +
            "WHERE (:search IS NULL OR email = :search) " +
            "OR (:search IS NULL OR phone_number = :search)",
            nativeQuery = true)
    List<User> searchUsers(@Param("search") String search);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.user.userId = :userId AND ur.role.roleId IN :roleIds")
    void deleteByUserUserIdAndRoleRoleIdIn(Long userId, List<Long> roleIds);

}