package Controllers;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
//import javax.swing.text.html.ImageView;
import java.awt.*;
import java.beans.EventHandler;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.scene.control.TextField;

import javafx.scene.control.Button;

import static Controllers.Controller.*;

public class LoggedInController extends ListView<String> implements Initializable
{
    @FXML
    ListView<String> myListView;

    String[] items=getNames();

    String role=getRole();

    String currentItem;

    String username=getUsername();

    String[] split;

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
                names[i++]=tok[0]+","+tok[1]+","+tok[2];
            }
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return names;
    }

    public String[] emptyArr(String[] arr)
    {
        String[] empty=new String[arr.length];
        return empty;
    }

    public void likeItems(javafx.event.ActionEvent event) throws IOException {
        if (currentItem!=null) {

            String[] getTheName = split[2].split("_");
            //System.out.println("The name is here: "+getTheName[0]);
            //System.out.println(split[0]+".."+split[1]+".."+split[2]+"--"+getTheName[0]);

            if (username.equals(getTheName[0]) == false) {
                int ok = 1;

                File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + username + "_likedItems.txt");
                Scanner reader = new Scanner(file);

                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    //System.out.println(data);
                    String[] tok = data.split(",");

                    if (split[0].equals(tok[0]) && split[1].equals(tok[1]) && split[2].equals(tok[2])) {
                        ok = 0;
                    }
                }

                if (ok == 1) {
                    File file2 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + username + "_likedItems.txt");
                    FileWriter writer = new FileWriter(file2, true);

                    writer.write(split[0] + "," + split[1] + "," + split[2] + "\n");
                    writer.close();
                }

                int found=0;

                file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + username + "_likedItems.txt");
                reader = new Scanner(file);

                while(reader.hasNextLine() && found==0)
                {
                    String data = reader.nextLine();
                    String[] tok = data.split(",");
                    String[] name = tok[2].split("_");

                    //System.out.println(name[0]+"...");

                    File file2 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + name[0] + "_likedItems.txt");
                    Scanner reader2 = new Scanner(file2);

                    //System.out.println(file2.length());

                    if(file2.length()!=0) {
                        while (reader2.hasNextLine() && found == 0) {
                            String data2 = reader2.nextLine();

                            //System.out.println(data2);

                            String[] tok2 = data2.split(",");
                            String[] name2 = tok2[2].split("_");
                            if (username.equals(name2[0])) {
                                found = 1;
                                //System.out.println("S-a gasit, yaaaay !!!!");

                                //continue with all 5 deletions here.. (deleting them from the liked lists, their personal lists and the "all items" list

                                String delEl1=split[0]+","+split[1]+","+split[2], delEl2=data2;

                                System.out.println(delEl1+"..."+delEl2);

                                String[] toCleanData=new String[64];
                                int o=0;

                                File del = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + username + "_likedItems.txt");
                                Scanner delRead = new Scanner(del);

                                while(delRead.hasNextLine())
                                {
                                    String delete=delRead.nextLine();

                                    //System.out.println("is it true ?..."+delEl2.equals(delete));

                                    if(delEl1.equals(delete)==false)
                                    {
                                        toCleanData[o++]=delete;
                                    }
                                }

                                FileWriter delWrite = new FileWriter(del);

                                for(String aux : toCleanData)
                                {
                                    if(aux!=null)
                                    {
                                        //System.out.println(aux+".. :)");
                                        delWrite.write(aux+"\n");
                                    }
                                }

                                delWrite.close();

                                o=0;
                                toCleanData=emptyArr(toCleanData);

                                del = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + getTheName[0] + "_likedItems.txt");
                                delRead = new Scanner(del);

                                while(delRead.hasNextLine())
                                {
                                    String delete=delRead.nextLine();

                                    //System.out.println("is it true ?..."+delEl2.equals(delete));

                                    if(delEl2.equals(delete)==false)
                                    {
                                        toCleanData[o++]=delete;
                                    }
                                }

                                delWrite = new FileWriter(del);

                                for(String aux : toCleanData)
                                {
                                    if(aux!=null)
                                    {
                                        //tem.out.println(aux+".. :)");
                                        delWrite.write(aux+"\n");
                                    }
                                }

                                delWrite.close();

                                o=0;
                                toCleanData=emptyArr(toCleanData);

                                del = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + username + "_items.txt");
                                delRead = new Scanner(del);

                                while(delRead.hasNextLine())
                                {
                                    String delete=delRead.nextLine();

                                    //System.out.println("is it true ?..."+delEl2.equals(delete));

                                    if(delEl2.equals(delete)==false)
                                    {
                                        toCleanData[o++]=delete;
                                    }
                                }

                                delWrite = new FileWriter(del);

                                for(String aux : toCleanData)
                                {
                                    if(aux!=null)
                                    {
                                        //System.out.println(aux+".. :)");
                                        delWrite.write(aux+"\n");
                                    }
                                }

                                delWrite.close();

                                o=0;
                                toCleanData=emptyArr(toCleanData);

                                del = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + getTheName[0] + "_items.txt");
                                delRead = new Scanner(del);

                                while(delRead.hasNextLine())
                                {
                                    String delete=delRead.nextLine();

                                    //System.out.println("is it true ?..."+delEl2.equals(delete));

                                    if(delEl1.equals(delete)==false)
                                    {
                                        toCleanData[o++]=delete;
                                    }
                                }

                                delWrite = new FileWriter(del);

                                for(String aux : toCleanData)
                                {
                                    if(aux!=null)
                                    {
                                        //System.out.println(aux+".. :)");
                                        delWrite.write(aux+"\n");
                                    }
                                }

                                delWrite.close();

                                o=0;
                                toCleanData=emptyArr(toCleanData);

                                del = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\all_items.txt");
                                delRead = new Scanner(del);

                                while(delRead.hasNextLine())
                                {
                                    String delete=delRead.nextLine();

                                    //System.out.println("is it true ?..."+delEl2.equals(delete));

                                    if(delEl2.equals(delete)==false && delEl1.equals(delete)==false)
                                    {
                                        toCleanData[o++]=delete;
                                    }
                                }

                                delWrite = new FileWriter(del);

                                for(String aux : toCleanData)
                                {
                                    if(aux!=null)
                                    {
                                        //System.out.println(aux+".. :)");
                                        delWrite.write(aux+"\n");
                                    }
                                }

                                delWrite.close();

                                if(role.equals("user"))
                                {
                                    switchToLoggedInScreen_user(event);
                                }

                                else if(role.equals("admin"))
                                {
                                    switchToLoggedInScreen_admin(event);
                                }
                                /*myListView.getItems().clear();
                                myListView.getItems().addAll(toCleanData);*/

                                String[] photo1=delEl1.split(",");
                                String[] photo2=delEl2.split(",");
                                Path imagesPath = Paths.get(System.getProperty("user.dir") + "\\photos\\"+photo1[2]);
                                Path imagesPath2 = Paths.get(System.getProperty("user.dir") + "\\photos\\"+photo2[2]);

                                try {
                                    Files.delete(imagesPath);
                                    Files.delete(imagesPath2);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    public void dislikeItem() throws FileNotFoundException {
        if (currentItem!=null)
        {
            int i=0;
            String[] newList = new String[64];

            File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + username + "_likedItems.txt");
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine())
            {
                String data = reader.nextLine();
                String[] tok = data.split(",");

                if(split[0].equals(tok[0])==false || split[1].equals(tok[1])==false || split[2].equals(tok[2])==false)
                {
                    newList[i++]=data;
                }
                try{
                    File file2 = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + username + "_likedItems.txt");
                    FileWriter writer = new FileWriter(file2);

                    for(String aux : newList)
                    {
                        if(aux != null)
                        {
                            writer.write(aux+"\n");
                        }
                    }

                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Cell extends ListCell<String>
    {
        HBox hbox = new HBox();
        //Button btn = new Button("Hei");

        ImageView img = new ImageView();

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
                //.out.println(name+" :)");
                String[] toks=name.split(",");

                label.setText(name);
                //System.out.println("."+toks[0]+".");

                try {
                    url= new File(System.getProperty("user.dir") + "\\photos\\"+toks[2]).toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                javafx.scene.image.Image profile = new Image(url.toString(), 40, 40, false, false);
                img.setImage(profile);

                label.setText(toks[0]+","+toks[1]);
                setGraphic(hbox);
            }
        }
    }

    public void initialize(URL arg0, ResourceBundle arg1)
    {
        myListView.getItems().addAll(items);

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                currentItem = (myListView.getSelectionModel().getSelectedItems()).toString();
                if(currentItem.equals("[null]")==false) {
                    split=currentItem.split(",");

                    split[0] = split[0].substring(1);
                    split[2] = split[2].substring(0, split[2].length() - 1);
                    //System.out.println(split[0]+"-"+split[1]+"-"+split[2]);
                }

                //System.out.println(currentItem);
            }
        });

        GridPane pane = new GridPane();
        Label name = new Label("h");
        //Button btn = new Button("Button");

        pane.add(name, 0, 0);
        //pane.add(btn, 0, 1);

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

    public void switchToUnapprovedItemScreen(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/approveItems.fxml"));
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

    public void switchToSearchScreen_user(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/searched_user.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSearchScreen_admin(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/searched_admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    public void switchToLoggedInScreen_admin(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmls/logged-in_admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
