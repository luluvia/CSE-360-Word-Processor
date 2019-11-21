package me.luyu.cse360;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Scribe extends Application
{

    private static Scene scene;
    public static final String VERSION = "0.0.1";

    @Override
    public void start(Stage stage) throws IOException
    {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.setTitle("Scribe Word Processor v" + VERSION);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException
    {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Scribe.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args)
    {
        launch();
    }

}