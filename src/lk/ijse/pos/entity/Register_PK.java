/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.entity;

/**
 *
 * @author SDMROX
 */
public class Register_PK {
    String cid,sid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Register_PK() {
    }

    public Register_PK(String cid, String sid) {
        this.cid = cid;
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Register_PK{" + "cid=" + cid + ", sid=" + sid + '}';
    }
    
}
