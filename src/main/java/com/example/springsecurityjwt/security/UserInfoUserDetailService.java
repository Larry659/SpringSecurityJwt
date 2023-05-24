package com.example.springsecurityjwt.security;

import com.example.springsecurityjwt.entity.UserInfo;
import com.example.springsecurityjwt.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component

public class UserInfoUserDetailService implements UserDetailsService {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
     return userInfo.map( UserInfoUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("user not found"));

    }
}
