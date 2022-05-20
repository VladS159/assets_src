package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class searchUserController extends ListView<String> implements Initializable
{
    private static Parent root;
    private static Stage stage;
    private static Scene scene;

    @FXML
    ListView<String> myListView3;

    @FXML
    TextField searchBar;

    String[] searchedList = new String[64];

    public void Search(javafx.event.ActionEvent event) throws IOException {
        int i=0;
        String keyword = searchBar.getText();

        if(keyword.equals("")==false)
        {
            for(int j=0;j<searchedList.length;j++)
            {
                searchedList[j]=null;
            }

            try
            {
                File wholeFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\all_items.txt");
                Scanner reader = new Scanner(wholeFile);

                while(reader.hasNextLine()) {
                    String data = reader.nextLine();
                    String[] toks = data.split(",");

                    //System.out.println(toks[0]+": "+toks[0].contains(keyword)+" - "+toks[1]+": "+toks[1].contains(keyword));

                    if (toks[0].contains(keyword) || toks[1].contains(keyword)) {
                        searchedList[i++] = toks[0]+","+toks[1]+","+toks[2];
                    }
                }

                //System.out.println(keyword);

                /*for(String aux : searchedList)
                {
                    if(aux!=null)
                    {
                        System.out.println(aux+" :)");
                    }
                }*/

                //objects=searchedList;

                myListView3.getItems().clear();
                myListView3.getItems().addAll(searchedList);
                //System.out.println("am ajuns aici :'(");
                //myListView3.getItems().addAll((Object[])searchedList);

                    //objects=searchedList;

            }catch(IOException e)
            {
                e.printStackTrace();
            }
            //switchToSearchScreen(event);
            //myListView3.getItems().addAll(empty);

        }
    }

    class Cell extends ListCell<String>
    {
        HBox hbox = new HBox();
        //Button btn = new Button("Hei");

        ImageView img = new ImageView();

        //ImageView img = new ImageView();

        Label label = new Label("");
        Pane pane = new Pane();

        public Cell()
        {
            super();

            hbox.getChildren().addAll(img, label, pane);
            hbox.setHgrow(pane, Priority.ALWAYS);
            //btn.setOnAction(e -> switchScene(e));
        }

        /*public void switchScene(javafx.event.ActionEvent event)
        {
            try {
                switchToLogInScreen(event);
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }*/

        public void updateItem(String name, boolean empty)
        {
            URL url = null;

            super.updateItem(name, empty);
            setText(null);
            setGraphic(null);

            if(name != null && !empty)
            {
                String[] toks=name.split(",");

                label.setText(toks[0]+","+toks[1]);
                //System.out.println("."+toks[0]+".");

                try {
                    url= new File(System.getProperty("user.dir") + "\\photos\\"+toks[2]).toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                javafx.scene.image.Image profile = new Image(url.toString(), 40, 40, false, false);
                img.setImage(profile);

                /*myListView3.refresh();
                myListView3.getItems().addAll((Object[])searchedList);*/

                label.setText(toks[0]+","+toks[1]);
                setGraphic(hbox);
            }
        }
    }

    public void initialize(URL arg0, ResourceBundle arg1)
    {
        /*for(String aux : searchedList)
        {
            if(aux!=null)
                System.out.println("..."+aux+"...");
        }

        myListView3.getItems().addAll(searchedList);*/
        //System.out.println("Heeei :DDD");

        GridPane pane = new GridPane();
        Label name = new Label("h");
        //Button btn = new Button("Button");

        pane.add(name, 0, 0);
        //pane.add(btn, 0, 1);

        myListView3.setCellFactory(param -> new Cell());
    }

    public void switchToSearchScreen(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/searched_user.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    public void switchToLoggedInScreen_user(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/logged-in_user.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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


    public void switchToAddItemScreen(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/add-item-page.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
