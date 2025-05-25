package application;

import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class homeScreenController {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Label dashUserCourse;

    @FXML
    private Label dashUserID;

    @FXML
    private Label dashUserName;
    
    @FXML
    private TableView<schedule> dashSched;
    
    @FXML 
    private TableColumn<schedule, String> dashSchedClass;
    
    @FXML 
    private TableColumn<schedule, String> dashSchedTime;
    
    private String userID;
    
    @FXML
    public void initialize() {
        dashSchedClass.setCellValueFactory(new PropertyValueFactory<>("className"));
        dashSchedTime.setCellValueFactory(new PropertyValueFactory<>("classTime"));
        
        dashSched.setItems(getScheduleData());
    }
    
    public void setUserID(String userID, String userPass, String userName, String userCourse) {
    	this.userID = userID;
    	dashUserName.setText(userName);
    	dashUserID.setText(userID + "@my.xu.edu.ph");
    	dashUserCourse.setText("BS" + userCourse);
    	
    	dashSched.setItems(getScheduleData());
    }
    
    public ObservableList<schedule> getScheduleData() {
        ObservableList<schedule> list = FXCollections.observableArrayList();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/slmis", "root", "");
            String sql = "SELECT className, classTime FROM schedule where userID =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                list.add(new schedule(
                    rs.getString("className"),
                    rs.getString("classTime")
                ));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}


