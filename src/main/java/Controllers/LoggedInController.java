package Controllers;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
//import javax.swing.text.html.ImageView;
import java.awt.*;
import java.beans.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.scene.control.Button;

public class LoggedInController extends ListView<String> implements Initializable
{
    @FXML
    ListView<String> myListView;

    String[] items=getNames();

    private static Parent root;
    private static Stage stage;
    private static Scene scene;

    public String[] getNames()
    {
        String[] names=new String[64];
        int i=0;

        try
        {
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\all_items.txt");
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()) {
                String data = reader.nextLine();

                String[] tok = data.split(",");
                names[i++]=tok[0]+", "+tok[1];
            }
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return names;
    }

    static class Cell extends ListCell<String>
    {
        HBox hbox = new HBox();
        Button btn = new Button("Hei");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell()
        {
            super();

            hbox.getChildren().addAll(label, pane ,btn);
            hbox.setHgrow(pane, Priority.ALWAYS);
            //btn.setOnAction();

        }

        public void updateItem(String name, boolean empty)
        {
            super.updateItem(name, empty);
            setText(null);
            setGraphic(null);

            if(name != null && !empty)
            {
                label.setText(name);
                setGraphic(hbox);
            }
        }
    }

    public void initialize(URL arg0, ResourceBundle arg1)
    {
        myListView.getItems().addAll(items);

        GridPane pane = new GridPane();
        Label name = new Label("h");
        Button btn = new Button("Button");

        pane.add(name, 0, 0);
        pane.add(btn, 0, 1);

        myListView.setCellFactory(param -> new Cell());
    }

    public void switchToAddItemScreen(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/add-item-page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void adminItems(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/admin-items.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void userItems(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/user-items.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogInScreen(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/sample.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
