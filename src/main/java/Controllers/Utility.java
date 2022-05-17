/*package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Utility {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String password)
    {
        Parent root=null;

        if(username!=null && password!=null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader(Utility.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username, password);
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        else
        {
            try
            {
                root = FXMLLoader.load(Utility.class.getResource(fxmlFile));
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password)
    {
        System.out.println("we have this name: "+username+" and this password: "+password);
        changeScene(event, "logged-in_user.fxml", "Welcom", username, password);
    }

    public static void logInUser(ActionEvent event, String username, String password)
    {
        System.out.println("Hei :)");
    }
}*/
