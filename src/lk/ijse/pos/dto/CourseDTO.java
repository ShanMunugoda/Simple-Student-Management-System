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
public class CourseDTO {
    private String cid,name,duration;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public CourseDTO() {
    }

    public CourseDTO(String cid, String name, String duration) {
        this.cid = cid;
        this.name = name;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CourseDTO{" + "cid=" + cid + ", name=" + name + ", duration=" + duration + '}';
    }
   
}
