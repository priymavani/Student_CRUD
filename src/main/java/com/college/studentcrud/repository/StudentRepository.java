package com.college.studentcrud.repository;

import com.college.studentcrud.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional; 

// databse operation perform krne ke liye repository banate hai
// like data save krna, data delete krna, data update krna, data get krna etc

@Repository // tell spring that this class will perform database operations
// repository tells spring to create the bean (object) in ioc container 
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // jparepository provide the methods to perform database operations like save, delete, update, find etc
    // JpaRepository is a interface which provides methods to perform database operations
    // Student is the entity class and Integer is the type of primary key

    List<Student> findByCourse(String course);
    // findByCourse is a method which will find the students by course name
    // it will find by query Select * from students where course = 'course name' (it will generate this query automatically)

    List<Student> findByNameContaining(String keyword);
    // findByNameContaining is a method which will find the students by name containing the keyword
    // it will find bt query Select * from student where name like '%keyword%' (it will generate this query automatically)

    // optional means that it will return the student if it is found otherwise it will return empty
    Optional<Student> findByEmail(String email);
    // findByEmail is a method which will find the student by email
    // it will find by query Select * from students where email = 'email' (it will generate this query automatically)

    List<Student> findByCourseOrderByName(String course);
    // findByCourseOrderByName is a method which will find the students by course name
    



     
}
