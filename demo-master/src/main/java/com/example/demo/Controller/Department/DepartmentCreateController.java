package com.example.demo.Controller.Department;

import com.example.demo.Main;
import com.example.demo.Repository.DepartmentRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DepartmentCreateController {
    final DepartmentRepository departmentRepository = new DepartmentRepository();

    @FXML
    public TextField Dname;
    @FXML
    public TextField Dnumber;
    @FXML
    public TextField Mgr_ssn;
    @FXML
    public DatePicker Mgr_start_date;

    public void insertDepartment(ActionEvent actionEvent){
        departmentRepository.insertDepartment(Dname.getText(),
                Dnumber.getText(),
                Mgr_ssn.getText(),
                String.valueOf(Mgr_start_date.getValue()));

        openDepartmentsPage(actionEvent);
    }

    public void openDepartmentsPage(ActionEvent event){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("departmentTable.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 450);
            stage.setTitle("База отделов");
            stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
