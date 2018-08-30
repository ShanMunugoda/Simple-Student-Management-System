/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.dto;

import java.util.Date;

/**
 *
 * @author SDMROX
 */
public class RegisterDTO {

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public RegisterDTO() {
    }
    
    public RegisterDTO(String sid, String cid, int fee) {
        this.sid = sid;
        this.cid = cid;
        this.fee = fee;
    }

    public RegisterDTO(String sid, String cid, Date regdate, int fee) {
        this.sid = sid;
        this.cid = cid;
        this.regdate = regdate;
        this.fee = fee;
    }
    private String sid,cid;
    private Date regdate;
    private int fee;

    @Override
    public String toString() {
        return "RegisterDTO{" + "sid=" + sid + ", cid=" + cid + ", regdate=" + regdate + ", fee=" + fee + '}';
    }
    
    
}
