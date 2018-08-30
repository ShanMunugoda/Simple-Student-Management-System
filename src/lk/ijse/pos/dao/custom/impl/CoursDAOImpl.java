/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.pos.dao.custom.CourseDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Course;

/**
 *
 * @author SDMROX
 */
public class CoursDAOImpl implements CourseDAO{

    @Override
    public boolean save(Course entity) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement("insert into course values(?,?,?)");
        Course course= entity;
        pstm.setObject(1, entity.getCid());
        pstm.setObject(2, entity.getName());
        pstm.setObject(3, entity.getDuration());
       return pstm.executeUpdate()>0;
        
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="update course set cid=?,name=?,duration=?";
        PreparedStatement pstm = conn.prepareStatement(quary);
         pstm.setObject(1, entity.getCid());
        pstm.setObject(2, entity.getName());
        pstm.setObject(3, entity.getDuration());
        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="delete from course where cid=?";
        PreparedStatement pstm = conn.prepareStatement(quary);
        pstm.setObject(1,id);
        return pstm.executeUpdate()>0;
    }

    @Override
    public Course find(String id) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="select * from course where cid=?";
        PreparedStatement pstm = conn.prepareStatement(quary);
        pstm.setObject(1, id);
        ResultSet rst = pstm.executeQuery();
        
        if(rst.next()){
            return new Course(rst.getString(1), rst.getString(2), rst.getString(3));
        }else{
        return null;
        }
        
    }

    @Override
    public ArrayList<Course> getAll() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery("select * from course");
        ArrayList<Course> arrayList = new ArrayList<>();
        while(rst.next()){
            Course course=new Course(rst.getString(1), rst.getString(2), rst.getString(3));
            arrayList.add(course);
        }
        return arrayList;
    }
    
    
}
