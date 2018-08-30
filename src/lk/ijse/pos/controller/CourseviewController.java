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
import lk.ijse.pos.dto.CourseDTO;
import lk.ijse.pos.main.StartUp;
import lk.ijse.pos.view.util.tblmodel.CourseTM;

/**
 * FXML Controller class
 *
 * @author SDMROX
 */
public class CourseviewController implements Initializable {

    @FXML
    private JFXTextField txtCname;
    @FXML
    private JFXTextField txtCid;
    @FXML
    private JFXTextField txtDuration;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<CourseTM> tblCourse;
    @FXML
    private AnchorPane root;

    
    private void loadAllCourses(){
        try {
            ArrayList<CourseDTO> allCourses = CourseBO.getAllCourses();
            
            ObservableList<CourseTM> olCourses = tblCourse.getItems();
            olCourses.removeAll(olCourses);
            
            for (CourseDTO course : allCourses) {
                CourseTM courseTM = new CourseTM(course.getCid(), course.getName(), course.getDuration());
                olCourses.add(courseTM);
            }
            
        } catch (Exception ex) {
            System.out.println("Error"+ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cid"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
        loadAllCourses();
    }    

    @FXML
    private void btnAddOnAction(ActionEvent event)  {
        String courseId=txtCid.getText();
        String courseName=txtCname.getText();
        String courseDuration=txtDuration.getText();
        CourseDTO courseDTO=new CourseDTO(courseId, courseName, courseDuration);
        
        try {
            boolean result = CourseBO.saveCourse(courseDTO);
            if(result){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved", ButtonType.OK).show();
                loadAllCourses();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseviewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        clearFields();
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
         String courseId = tblCourse.getSelectionModel().getSelectedItem().getCid();
        
        try {
            boolean result = CourseBO.deleteCourse(courseId);
            
            if (result){
                new Alert(Alert.AlertType.CONFIRMATION, "Course has been deleted successfully", ButtonType.OK).show();
                loadAllCourses();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete the Course", ButtonType.OK).show();
            }            
        } catch (Exception ex) {
            System.out.println("Error"+ex);
        }
        
    }

    private void clearFields(){
        txtCid.setText("");
        txtCname.setText("");
        txtDuration.setText("");

    }

    @FXML
    private void navigateToHome(MouseEvent event) {
        StartUp.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }
    
}
