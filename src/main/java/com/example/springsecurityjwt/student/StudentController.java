package com.example.springsecurityjwt.student;

import com.example.springsecurityjwt.entity.UserInfo;
import com.example.springsecurityjwt.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final List<Student> studentList = List.of(
            new Student(1,"Adewale","ogun"),
            new Student(2,"Samuel","oyo"),
            new Student(3,"Kingsley","abia")
    );
    @GetMapping(path = "/{studentId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public Student getStudent(@PathVariable("studentId") Integer id){
        return studentList.stream()
                .filter(stud->id.equals(stud.getId()))
                .findAny()
                .orElseThrow(()-> new IllegalStateException("Student with " + id + " not found"));
    }
    @PostMapping("/add_user")
    public String addUser(@RequestBody UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User saved successfully";
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") //This annotation will make this method accessible by ADMIN only.
    public List<Student> getAllStudents(){
        return studentList;
    }
    @GetMapping("/greet")
    public String greetUser(){
        return "Good morning this endpoint is not secured";
    }
}
