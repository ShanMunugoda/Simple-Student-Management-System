/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business;

import java.util.ArrayList;
import lk.ijse.pos.dao.custom.CourseDAO;
import lk.ijse.pos.dao.custom.impl.CoursDAOImpl;
import lk.ijse.pos.dto.CourseDTO;
import lk.ijse.pos.entity.Course;

/**
 *
 * @author SDMROX
 */
public class CourseBO {
    private static CourseDAO courseDAO=new CoursDAOImpl();
    
    public static boolean saveCourse(CourseDTO dto) throws Exception{
        Course course=new Course(dto.getCid(), dto.getName(), dto.getDuration());
        return courseDAO.save(course);
    }
    
    public static ArrayList<CourseDTO> getAllCourses() throws Exception{
        ArrayList<Course> allCourses = courseDAO.getAll();
        ArrayList<CourseDTO> dtos=new ArrayList<>();
        for (Course course : allCourses) {
            CourseDTO courseDTO=new CourseDTO(course.getCid(), course.getName(),course.getDuration());
            dtos.add(courseDTO);
        }
        return dtos;
    }
    
    public static boolean deleteCourse(String courseId) throws Exception{
        return courseDAO.delete(courseId);
    
    }
    
}