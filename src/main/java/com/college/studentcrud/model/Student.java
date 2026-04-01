package com.college.studentcrud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity // tell spring that this class will create  table in database
@Table(name = "students") // set table name in database
// if you dont write name = "students" then it will take class name as table name

public class Student {

    @Id // set primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment id
    private int id;

    @Column(nullable = false, length = 100) // cloumn name in table, not null and length of column
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String course;

    @Column(length = 15)
    private String phone;

    public Student() {

    }
    // dydefalut constructor is required for JPA to create objects from database records


    public Student( String name, String email, String course, String phone) {
        
        this.name = name;
        this.email = email;
        this.course = course;
        this.phone = phone;
    }
    // make paramete constructor help to create student object

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public String getPhone() { return phone; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setCourse(String course) { this.course = course; }
    public void setPhone(String phone) { this.phone = phone; }







}
// table structure

