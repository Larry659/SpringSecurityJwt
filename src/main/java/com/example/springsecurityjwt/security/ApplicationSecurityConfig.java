package com.example.springsecurityjwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //This annotation makes it possible for authorization of endpoints
public class ApplicationSecurityConfig {
    //Authentication
    //The UserDetailService gives us demo users since database is yet to be integrated with our application

    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("kunlebalo")
//                            .username("kunlebalo")
//                             .password(passwordEncoder.encode("KunleBalo26"))
//                              .roles("ADMIN")
//                                 .build();
//        UserDetails user = User.withUsername("samueljackson")
//                .username("Biggysam")
//                .password(passwordEncoder.encode("BigSam45"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin,user);
//        UserDetails simpleUser = User.builder()
//                .username("samueljackson")
//                .password("BiggySam45")
//                .roles("USER")
//                .build();
        return new UserInfoUserDetailService();
    }
    /*
    * The below implementation is where authorization is being done
    * Some endpoints and pages can be accessed without login(authentication)
    * while some need authenticated users only to access it (authentication)
    * Some also need a special type of user to access them (authorization)*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     return   http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/students/greet","/students/add_user").permitAll() //allow all url with this/these pattern(s)
                .and()
                .authorizeHttpRequests().requestMatchers("/students/**")
                .authenticated().and().formLogin().and().build();//Only authenticated users can access this/these endpoints with
              //the stipulated patterns

    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder(12);
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

}
