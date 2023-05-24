package com.example.springsecurityjwt.student;

public class Student {
    private Integer id;
    private String name;
    private String state;

    public Student(Integer id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return state;
    }

    public void setAge(String state) {
        this.state = state;
    }
}
