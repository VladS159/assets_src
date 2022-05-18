import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage)
    {
        try {
            File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Database.txt");
            if (file.exists() == false) {
                file.createNewFile();
            }

            //System.out.println(System.getProperty("user.dir") + "\\src\\main\\resources\\all_items.txt");
            file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\all_items.txt");
            if (file.exists() == false) {
                file.createNewFile();
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("fxmls/sample.fxml"));  //C:\Users\Vlad\Desktop\gradle_test\src\main\resources\Database.txt
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
