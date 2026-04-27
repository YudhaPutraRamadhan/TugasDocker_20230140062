package com.example.pertemuan6.repository;

import com.example.pertemuan6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}