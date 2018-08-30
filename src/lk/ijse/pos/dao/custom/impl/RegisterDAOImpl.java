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
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.Register;
import lk.ijse.pos.entity.Register_PK;
import lk.ijse.pos.dao.custom.RegisterDAO;

/**
 *
 * @author SDMROX
 */
public class RegisterDAOImpl implements RegisterDAO{

    @Override
    public boolean save(Register entity) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="insert into registration values(?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(quary);
        Register register=entity;
        pstm.setObject(1, entity.getFee());
        pstm.setObject(2, entity.getRegdate());
        pstm.setObject(3, entity.getRegister_pk().getSid());
        pstm.setObject(4, entity.getRegister_pk().getCid());
        
        return pstm.executeUpdate()>0;
        
    }

    @Override
    public boolean update(Register entity) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="update registration set fee=?,regdate=curDate where cid=? and sid=?";
        PreparedStatement pstm = conn.prepareStatement(quary);
        pstm.setObject(1, entity.getFee());
        pstm.setObject(2, entity.getRegister_pk().getCid());
        pstm.setObject(3, entity.getRegister_pk().getSid());
        return pstm.executeUpdate()>0;
        
    }

    @Override
    public boolean delete(Register_PK id) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="delete from registration where cid=? and sid=?";
        PreparedStatement pstm = conn.prepareStatement(quary);
        pstm.setObject(1, id.getCid());
        pstm.setObject(2, id.getSid());
        return pstm.executeUpdate()>0;
    }

    @Override
    public Register find(Register_PK id) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="select * from registration where cid=? and sid=?";
        PreparedStatement pstm = conn.prepareStatement(quary);
        pstm.setObject(1, id.getCid());
        pstm.setObject(1, id.getSid());
        ResultSet rst = pstm.executeQuery();
   
        if(rst.next()){
            return new Register(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDate(4));
        }
        return null;
        
        
    }

    @Override
    public ArrayList<Register> getAll() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String quary="select * from registration";
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery(quary);
        ArrayList<Register> arr = new ArrayList<>();
        while(rst.next()){
            Register register = new Register(rst.getString(4), rst.getString(3), rst.getInt(1), rst.getDate(2));
            arr.add(register);
        }
        return arr;
    }
    
}
