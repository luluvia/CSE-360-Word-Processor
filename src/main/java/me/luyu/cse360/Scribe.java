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
    private static Stage pStage;
    public static final String VERSION = "0.0.1";

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        pStage = primaryStage;
        scene = new Scene(loadFXML("primary"));
        pStage.setScene(scene);
        pStage.setTitle("Scribe Word Processor v" + VERSION);
        pStage.show();
    }

    public static Stage getPrimaryStage()
    {
        return pStage;
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