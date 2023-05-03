package com.den.MySpringSecurityTuts.repository;

import com.den.MySpringSecurityTuts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "select * from users where name = ?1", nativeQuery = true)
    Optional<User> findUserByName(String name);
}
