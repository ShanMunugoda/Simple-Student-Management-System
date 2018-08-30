/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business;

import java.util.ArrayList;
import lk.ijse.pos.dao.custom.RegisterDAO;
import lk.ijse.pos.dao.custom.impl.RegisterDAOImpl;
import lk.ijse.pos.dto.RegisterDTO;
import lk.ijse.pos.entity.Course;
import lk.ijse.pos.entity.Register;
import lk.ijse.pos.entity.Register_PK;

/**
 *
 * @author SDMROX
 */
public class RegisterBO {
     private static RegisterDAO registerDAO=new RegisterDAOImpl();
     
     public static boolean saveRegister(RegisterDTO dto) throws Exception{
        Register register=new Register(dto.getCid(), dto.getSid(), dto.getFee(), dto.getRegdate());
        return registerDAO.save(register);
     }
     
      public static ArrayList<RegisterDTO> getAllRegisters() throws Exception{
        ArrayList<Register> allRegisters = registerDAO.getAll();
        ArrayList<RegisterDTO> dtos = new ArrayList<>();
        
          for (Register Register : allRegisters) {
            RegisterDTO registerDTO = new RegisterDTO(Register.getRegister_pk().getSid(), Register.getRegister_pk().getCid(), Register.getRegdate(), Register.getFee());
              dtos.add(registerDTO);
          }
 
        return dtos;
        
        
    }

      public static boolean deleteRegister(String sid,String cid) throws Exception{
         Register_PK pk = new Register_PK(cid, sid);
        
        return registerDAO.delete(pk);
      
      
      } 
}
