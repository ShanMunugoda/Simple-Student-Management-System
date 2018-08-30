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
import lk.ijse.pos.dao.custom.StudentDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Course;
import lk.ijse.pos.entity.Student;

/**
 *
 * @author SDMROX
 */
public class StudentDAOImpl implements StudentDAO{

    @Override
    public boolean save(Student entity) throws Exception {
       Connection conn = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement("insert into student values(?,?,?)");
        Student student= entity;
        pstm.setObject(1, entity.getSid());
        pstm.setObject(2, entity.getName());
        pstm.setObject(3, entity.getAddress());
       return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="update student set sid=?,name=?,address=?";
        PreparedStatement pstm = conn.prepareStatement(quary);
         pstm.setObject(1, entity.getSid());
        pstm.setObject(2, entity.getName());
        pstm.setObject(3, entity.getAddress());
        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="delete from student where sid=?";
        PreparedStatement pstm = conn.prepareStatement(quary);
        pstm.setObject(1,id);
        return pstm.executeUpdate()>0;
    }

    @Override
    public Student find(String id) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="select * from student where sid=?";
        PreparedStatement pstm = conn.prepareStatement(quary);
        pstm.setObject(1, id);
        ResultSet rst = pstm.executeQuery();
        
        if(rst.next()){
            return new Student(rst.getString(1), rst.getString(2), rst.getString(2));
        }else{
        return null;
        }
    }

    @Override
    public ArrayList<Student> getAll() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery("select * from student");
        ArrayList<Student> arrayList = new ArrayList<>();
        while(rst.next()){
            Student student=new Student(rst.getString(1), rst.getString(2), rst.getString(3));
            arrayList.add(student);
        }
        return arrayList;
    }

   
    
    
}
