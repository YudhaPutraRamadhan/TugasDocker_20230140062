package com.example.pertemuan6.repository;

import com.example.pertemuan6.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}