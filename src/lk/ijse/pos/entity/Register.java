/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.entity;

import java.util.Date;

/**
 *
 * @author SDMROX
 */
public class Register {
    private Register_PK register_pk;

    public Register_PK getRegister_pk() {
        return register_pk;
    }

    public void setRegister_pk(Register_PK register_pk) {
        this.register_pk = register_pk;
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

    public Register() {
    }

    public Register(Register_PK register_pk, int fee, Date regdate) {
        this.register_pk = register_pk;
        this.fee = fee;
        this.regdate = regdate;
    }
    public Register(String cid,String sid,int fee,Date regdate){
        this.register_pk=new Register_PK(cid, sid);
        this.regdate=regdate;
        this.fee=fee;
    
    }
    
     public Register(String cid,String sid) {
        this.register_pk=new Register_PK(cid, sid);
       
    }
    private int fee;
    private Date regdate;

    @Override
    public String toString() {
        return "Register{" + "register_pk=" + register_pk + ", fee=" + fee + ", regdate=" + regdate + '}';
    }
    
    
}
