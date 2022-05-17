package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable
{
    @FXML
    TextField tf_username;

    @FXML
    PasswordField tf_password;

    @FXML
    ChoiceBox<String> myChoiceBox;
    private String[] users = {"admin", "user"};

    static String role;

    public void initialize(URL arg0, ResourceBundle arg1)
    {
        myChoiceBox.getItems().addAll(users);
        myChoiceBox.setOnAction(this::getRole);
    }

    public void getRole(javafx.event.ActionEvent event)
    {
        this.role=myChoiceBox.getValue();
    }

   private Stage stage;
   private Scene scene;
   private Parent root;

    public static String username, password;

   /*public void switchToLogInScreen(javafx.event.ActionEvent event) throws IOException
   {
       root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
   }*/

    public void logIn(javafx.event.ActionEvent event) throws FileNotFoundException
    {
        username=tf_username.getText();
        password=tf_password.getText();

        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Database.txt");
        Scanner reader = new Scanner(file);

        int ok=0;

        if(password!=null && username!=null && role!=null) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] tok = data.split(" ");

                String temp_username=username.hashCode()+"", temp_password=(password+tok[0]).hashCode()+"", temp_role=role.hashCode()+"";

                if (tok[1].equals(temp_username) && tok[2].equals(temp_password) && tok[3].equals(temp_role)) {
                    ok = 1;
                }
            }
        }

        if(ok==1)
        {
            try {
                if(role=="admin") {
                    switchToLoggedInScreen_admin(event);
                }

                else if(role=="user")
                {
                    switchToLoggedInScreen_user(event);
                }
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static String getRole()
    {
        return role;
    }

    public static String getUsername()
    {
        return username;
    }

   public void switchToLoggedInScreen_user(javafx.event.ActionEvent event) throws IOException {
       root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/logged-in_user.fxml"));
       stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
   }

    public void switchToLoggedInScreen_admin(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/logged-in_admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   public void switchToSignUpScreen(javafx.event.ActionEvent event) throws IOException
   {
       root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/sign-up.fxml"));
       stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
   }
}
