/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business;

import java.util.ArrayList;
import lk.ijse.pos.dao.custom.StudentDAO;
import lk.ijse.pos.dao.custom.impl.StudentDAOImpl;
import lk.ijse.pos.dto.CourseDTO;
import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.entity.Course;
import lk.ijse.pos.entity.Student;

/**
 *
 * @author SDMROX
 */
public class StudentBO {
    private static StudentDAO studentDAO=new StudentDAOImpl();
    
    public static boolean saveStudents(StudentDTO dto) throws Exception{
        Student student=new Student(dto.getSid(), dto.getName(), dto.getAddress());
        return studentDAO.save(student);
    }
    
    public static ArrayList<StudentDTO> getAllStudents() throws Exception{
        ArrayList<Student> allCourses = studentDAO.getAll();
        ArrayList<StudentDTO> dtos=new ArrayList<>();
        for (Student student : allCourses) {
            StudentDTO studentDTO=new StudentDTO(student.getSid(), student.getName(), student.getAddress());
            dtos.add(studentDTO);
            
        }
         return dtos;
    }
    
    public static boolean deleteStudent(String studentId) throws Exception{
        return studentDAO.delete(studentId);
    
    }
    
}
