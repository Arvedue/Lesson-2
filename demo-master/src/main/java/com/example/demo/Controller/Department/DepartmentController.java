package com.example.demo.Controller.Department;

import com.example.demo.Controller.Employee.EmployeeEditController;
import com.example.demo.Main;
import com.example.demo.Model.Department;
import com.example.demo.Model.Employee;
import com.example.demo.Repository.DepartmentRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class DepartmentController {
    final DepartmentRepository departmentRepository = new DepartmentRepository();
    @FXML
    public TableView<Department> departmentTable;
    @FXML
    public TableColumn<Department, String> dNameColumn;
    @FXML
    public TableColumn<Department, String> mgrSsnColumn;
    @FXML
    public TableColumn<Department, Date> mgrStartDateColumn;
    @FXML
    public TableColumn<Department, Department> editColumn;
    @FXML
    public TableColumn<Department, Department> deleteColumn;

    @FXML
    public void initialize(){
        dNameColumn.setCellValueFactory(cellData -> cellData.getValue().dnameProperty());
        mgrSsnColumn.setCellValueFactory(cellData -> cellData.getValue().mgr_ssnProperty());
        mgrStartDateColumn.setCellValueFactory(cellData -> cellData.getValue().mgr_start_dateProperty());
        editColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        deleteColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));

        initializeTableValues();

        editColumn.setCellFactory(param -> new TableCell<Department, Department>() {
            private final Button editButton = new Button("Изменить");

            @Override
            protected void updateItem(Department department, boolean empty) {
                super.updateItem(department, empty);

                if (department == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);

                editButton.setOnAction(event -> editDepartment(event, department));
            }
        });

        deleteColumn.setCellFactory(param -> new TableCell<Department, Department>() {
            private final Button deleteButton = new Button("Удалить");

            @Override
            protected void updateItem(Department department, boolean empty) {
                super.updateItem(department, empty);

                if (department == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);

                deleteButton.setOnAction(event -> removeDepartment(department.getDnumber()));
            }
        });
    }

    public void removeDepartment(String dnumber){
        departmentRepository.removeDepartment(dnumber);
        initializeTableValues();
    }

    public void editDepartment(ActionEvent event, Department department) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("departmentEdit.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 450);
            stage.setTitle("Изменение отдела");
            stage.setScene(scene);
            DepartmentEditController controller = fxmlLoader.<DepartmentEditController>getController();
            controller.setDepartment(department);
            stage.show();

            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeTableValues(){
        ObservableList<Department> departments = departmentRepository.getList();
        if(departments.size() > 0){
            departmentTable.setItems(departments);
        }
    }

    public void openCreatePage(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("departmentCreate.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 450);
            stage.setTitle("Добавление отдела");
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
