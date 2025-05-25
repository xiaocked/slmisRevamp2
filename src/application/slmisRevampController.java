package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class slmisRevampController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginIDTxt;

    @FXML
    private PasswordField loginPassTxt;
    
    @FXML
    private Label loginLabel;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    void login(ActionEvent event) {
    	
    	String userID = loginIDTxt.getText();
    	String userPass = loginPassTxt.getText();
    	
    	if(userID.equals("") && userPass.equals("")) {
    		loginLabel.setText("Incorrect ID or Password.");
		} else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/slmis", "root", "");
				pst = con.prepareStatement("select userName, userCourse from login where userID = ? and userPass = ?");
				
				pst.setString(1, userID);
				pst.setString(2, userPass);
				
				rs = pst.executeQuery();
				
				if(rs.next()) {
					String userName = rs.getString("userName");
					String userCourse = rs.getString("userCourse");
					DBUtils.changeScene(event, "/application/homeScreen.fxml", "Welcome", userID, userPass, userName, userCourse);
				}
				else {
					loginLabel.setText("Incorrect ID or Password.");
					loginIDTxt.setText("");
					loginPassTxt.setText("");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
    }

}
