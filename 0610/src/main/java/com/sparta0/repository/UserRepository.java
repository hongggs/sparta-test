package com.sparta0.repository;

import com.sparta0.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByGenderAndAge(int gender, int age);
}
