/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.dto;

/**
 *
 * @author SDMROX
 */
public class StudentDTO {
    private String sid,name,address;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StudentDTO() {
    }

    public StudentDTO(String sid, String name, String address) {
        this.sid = sid;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "StudentDTO{" + "sid=" + sid + ", name=" + name + ", address=" + address + '}';
    }
    
    
}
