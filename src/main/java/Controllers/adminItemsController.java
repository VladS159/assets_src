package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import static Controllers.Controller.getUsername;

public class adminItemsController implements Initializable
{
    @FXML
    ListView<String> myListView;

    private static Parent root;
    private static Stage stage;
    private static Scene scene;

    String[] items=getNames();

    public String[] getNames()
    {
        String[] names=new String[64];
        int i=0;

        String username=getUsername();

        try
        {
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\"+username+"_items.txt");
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

    public void initialize(URL arg0, ResourceBundle arg1) {
        myListView.getItems().addAll(items);

        GridPane pane = new GridPane();
        Label name = new Label("h");
        Button btn = new Button("Button");

        pane.add(name, 0, 0);
        pane.add(btn, 0, 1);

        myListView.setCellFactory(param -> new LoggedInController.Cell());
    }

    public void switchToAddItemScreen(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/add-item-page.fxml"));
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
