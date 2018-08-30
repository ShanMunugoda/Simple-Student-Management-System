/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.view.util.tblmodel;

import java.util.Date;

/**
 *
 * @author SDMROX
 */
public class RegisterTM {
    private String sid,cid;
    private int fee;
    private Date regdate;

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

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public RegisterTM() {
    }

    public RegisterTM(String sid, String cid, int fee, Date regdate) {
        this.sid = sid;
        this.cid = cid;
        this.fee = fee;
        this.regdate = regdate;
    }
    
    
}
