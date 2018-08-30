/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import lk.ijse.pos.business.StudentBO;
import lk.ijse.pos.dto.StudentDTO;
import lk.ijse.pos.main.StartUp;
import lk.ijse.pos.view.util.tblmodel.StudentTM;

/**
 * FXML Controller class
 *
 * @author SDMROX
 */
public class StudentviewController implements Initializable {

    @FXML
    private JFXTextField txtSid;
    @FXML
    private JFXTextField txtSname;
    @FXML
    private JFXTextField txtSaddress;
    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<StudentTM> tblStudent;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("sid"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        loadAllStudents();
    }    

    @FXML
    private void btnaddOnAction(ActionEvent event) {
        String studentId=txtSid.getText();
        String studentName=txtSname.getText();
        String studentDuration=txtSaddress.getText();
        StudentDTO studentDTO=new StudentDTO(studentId, studentName, studentDuration);
        
        try {
            boolean result = StudentBO.saveStudents(studentDTO);
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved", ButtonType.OK).show();
                loadAllStudents();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to save the Student", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseviewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        clearAllFields();
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
         String studentId = tblStudent.getSelectionModel().getSelectedItem().getSid();
        
        try {
            boolean result = StudentBO.deleteStudent(studentId);
            
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION, "Course has been deleted successfully", ButtonType.OK).show();
                loadAllStudents();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete the Course", ButtonType.OK).show();
            }            
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR,"This student is in a Course",ButtonType.OK).show();
            System.out.println("Error"+ex);
        }
    }
    
      private void loadAllStudents(){
        try {
            ArrayList<StudentDTO> allStudents = StudentBO.getAllStudents();
            
            ObservableList<StudentTM> olStudents = tblStudent.getItems();
            olStudents.removeAll(olStudents);
            
            for (StudentDTO student : allStudents) {
                StudentTM studentTM = new StudentTM(student.getSid(), student.getName(), student.getAddress());
                olStudents.add(studentTM);
            }
            
        } catch (Exception ex) {
            System.out.println("Error"+ex);
        }
     }

    @FXML
    private void navigateToHome(MouseEvent event) {
        StartUp.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }


        private void clearAllFields(){
        txtSid.setText("");
        txtSname.setText("");
        txtSaddress.setText("");
        }
}
