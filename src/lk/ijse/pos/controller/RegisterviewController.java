/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.business.CourseBO;
import lk.ijse.pos.business.RegisterBO;
import lk.ijse.pos.business.StudentBO;
import lk.ijse.pos.dto.CourseDTO;
import lk.ijse.pos.dto.RegisterDTO;
import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.main.StartUp;
import lk.ijse.pos.view.util.tblmodel.RegisterTM;

/**
 * FXML Controller class
 *
 * @author SDMROX
 */
public class RegisterviewController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbCid;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private TableView<RegisterTM> tblRegistration;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTextField txtRegFee;
    @FXML
    private JFXComboBox<String> cmbStudentId;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    private void loadAllRegister(){
        
        try {
            
           ArrayList<RegisterDTO>   allRegisters = RegisterBO.getAllRegisters();
            ObservableList<RegisterTM> alRegister=tblRegistration.getItems();
         alRegister.removeAll(alRegister);
         for (RegisterDTO register : allRegisters) {
             RegisterTM registerTM=new RegisterTM(register.getSid(), register.getCid(), register.getFee(), register.getRegdate());
           alRegister.add(registerTM);  
            
        }
        } catch (Exception ex) {
            Logger.getLogger(RegisterviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblRegistration.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("sid"));
        tblRegistration.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cid"));
        tblRegistration.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("fee"));
        tblRegistration.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("regdate"));
        
        loadAllRegister();
        loadcmbSid();
       loadCmbCid();
        
    }    

    @FXML
    private void btnAddOnAction(ActionEvent event) {
         save();
         loadAllRegister();
         clearFields();
    }

    @FXML
    private void cmbCidOnAction(ActionEvent event) {
        
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        deleteTblRecord();
         loadAllRegister();
         clearFields();
    }
    
    private void deleteTblRecord(){
        RegisterTM selectedItem = tblRegistration.getSelectionModel().getSelectedItem();
        
        String sid = selectedItem.getSid();
        String cid = selectedItem.getCid();
        
        try {
            boolean rst = RegisterBO.deleteRegister(sid, cid);
            if(rst){
                 new Alert(Alert.AlertType.CONFIRMATION, "Record Deleted.....!", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "ERROR....Not Deleted.....!", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void loadCmbCid(){
    
        try {
            ArrayList<CourseDTO> allCourses = CourseBO.getAllCourses();
            ArrayList<String> cids=new ArrayList<>();
            for (CourseDTO allCourse : allCourses) {
                cids.add(allCourse.getCid());
            }
            cmbCid.setItems(FXCollections.observableArrayList(cids));
        } catch (Exception ex) {
            Logger.getLogger(RegisterviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
     
    private void clearFields(){
            cmbCid.setValue("");
            cmbStudentId.setValue("");
            txtRegFee.setText("");
            
    }
    
    private void loadcmbSid(){
        
        try {
            ArrayList<StudentDTO> allStudents =StudentBO.getAllStudents();
            ArrayList<String> sids=new ArrayList<>();
            for (StudentDTO allStudent : allStudents) {
                sids.add(allStudent.getSid());
            }
            cmbStudentId.setItems(FXCollections.observableArrayList(sids));
        } catch (Exception ex) {
            Logger.getLogger(RegisterviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    private void save(){
        String sid = cmbStudentId.getValue();
        String cid = cmbCid.getValue();
        int fee =Integer.parseInt(txtRegFee.getText());
        
        
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String currentDate=sd.format(date);
        
        RegisterDTO registerDTO = new RegisterDTO(sid, cid, date, fee);
        try {
            boolean rst = RegisterBO.saveRegister(registerDTO);
            
            if(rst){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved", ButtonType.OK).show();
            }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Ops..Not saved", ButtonType.OK).show();
            
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void cmbSidOnAction(ActionEvent event) {
    }

    @FXML
    private void navigateToHome(MouseEvent event) {
        StartUp.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }
    
}
