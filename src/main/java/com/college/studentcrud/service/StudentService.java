package com.college.studentcrud.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.college.studentcrud.model.Student;
import com.college.studentcrud.repository.StudentRepository;


import java.util.List;
import java.util.Optional; 

@Service // tell spring that this class is service class and it will contain business logic
public class StudentService {
    
    private final StudentRepository studentRepository;
    
    @Autowired // tell spring to inject the dependency of studentRepository
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
 
    // 
    public Student addStudent(Student student){
        // save method will save the student object in database and return the saved object with id
        return studentRepository.save(student);
        // save will exceute insert query in database and return the saved object
        // like insert into students (name, email, course, phone) values (?, ?, ?, ?) 
    }


    public List<Student> getAllStudents(){

        return studentRepository.findAll();
        // findAll method will return all the records from students table as a list of student objects
        // like select * from student
    }

    public Optional<Student> getStudentById(int id){

        return studentRepository.findById(id);
        // findById method will return the student object with the given id if it exists in database otherwise it will return empty optional
        // like select * from student where id = ?

        //why optional because if we student does not exist in database then it will return null and it will throw null pointer exception if we try to access the student object without checking if it is null or not
        // so to avoid null pointer exception we use optional which will return empty 
    }

    public List<Student> getStudentByCourse(String course){

        return studentRepository.findByCourse(course);
        // findByCourse method will return the list of student objects who are enrolled in the given course
        // like select * from student where course = ?
    }

    public List<Student> searchByName(String keyword){

        return studentRepository.findByNameContaining(keyword);
        // findByNameContaining method will return the list of student objects whose name contains the given keyword
        // like select * from student where name like %?%
    }

    public Optional<Student> updateStudent(int id , Student updateData){

        // in this function we provide the id and updatedata


        Optional<Student> existingOpt = studentRepository.findById(id);
        // find the student by id if it exists in database otherwise it will return empty optional

        if(existingOpt.isPresent()){
            // if the student exists in database then we will update the student data and save it in database


            Student existing = existingOpt.get();
            // fetch all the data from existingOpt 

            existing.setName(updateData.getName());
            existing.setEmail(updateData.getEmail());
            existing.setCourse(updateData.getCourse());
            existing.setPhone(updateData.getPhone());

            Student saved = studentRepository.save(existing);
            // save method will update the student object in database and return the updated object with id
            return Optional.of(saved);
            // optional.of will return the updated student object wrapped in optional


        }
        return Optional.empty();

    }

    public boolean deleteStudent(int id){

        if(studentRepository.existsById(id)){
            // existsById method will check if the student with the given id exists in database or not
            studentRepository.deleteById(id);
            // deleteById method will delete the student with the given id from database
            return true;
        }
        return false;
    }

    public long getTotalCount(){
        return studentRepository.count();
        // count method will return the total number of records in students table
        // like select count(*) from student
    }   




}
