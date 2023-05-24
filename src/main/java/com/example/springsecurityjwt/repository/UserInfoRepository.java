package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByName(String username);
}
