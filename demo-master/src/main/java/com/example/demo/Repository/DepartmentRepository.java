package com.example.demo.Repository;

import com.example.demo.Connection.ConnectionClass;
import com.example.demo.Model.Department;
import com.example.demo.Model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentRepository {
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

    public ObservableList<Department> getList(){
        ObservableList<Department> list = FXCollections.observableArrayList();
        try {
            Statement statement=connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String sql="SELECT * FROM department;";

            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                resultSet.previous();
                while (resultSet.next()) {
                    Department department = new Department(
                            resultSet.getString("Dname"),
                            resultSet.getString("Dnumber"),
                            resultSet.getString("Mgr_ssn"),
                            resultSet.getDate("Mgr_start_date")
                    );
                    list.add(department);
                }
            }else {
                System.out.println("no data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Department getByNumber(String dNumber){
        Department department = null;
        try {
            Statement statement=connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String sql="SELECT * FROM department WHERE Dnumber = '"+dNumber+"' LIMIT 1;";

            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                resultSet.previous();
                while (resultSet.next()) {
                    Department dept = new Department(
                            resultSet.getString("Dname"),
                            resultSet.getString("Dnumber"),
                            resultSet.getString("Mgr_ssn"),
                            resultSet.getDate("Mgr_start_date")
                    );
                    department = dept;
                }
            }else {
                System.out.println("no data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    public void removeDepartment(String dNumber) {
        try {
            Statement statement=connection.createStatement();
            String sql = "DELETE FROM department WHERE Dnumber = '" + dNumber + "'";
            statement.executeUpdate(sql);

            System.out.println("Success!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDepartment(String Dname, String Dnumber, String Mgr_ssn, String Mgr_start_date) {
        try {
            Statement statement=connection.createStatement();
            String sql = "INSERT INTO " +
                    "department(Dname, Dnumber, Mgr_ssn, Mgr_start_date) " +
                    "VALUES ('"+Dname+"','" +
                    ""+Dnumber+"','" +
                    ""+Mgr_ssn+"','" +
                    ""+Mgr_start_date+"')";
            statement.executeUpdate(sql);

            System.out.println("Success!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDepartment(String Dname, String Dnumber, String Mgr_ssn, String Mgr_start_date){
        try {
            Statement statement=connection.createStatement();
            String sql = "UPDATE department SET  Dname = '"+Dname+"', Mgr_ssn = '"+Mgr_ssn+"', Mgr_start_date = '"+Mgr_start_date+"' WHERE Dnumber = '"+Dnumber+"';";
            statement.executeUpdate(sql);

            System.out.println("Successfully updated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
