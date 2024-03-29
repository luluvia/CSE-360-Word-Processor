package me.luyu.cse360;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PrimaryController
{
    @FXML
    private TabPane tabPane;

    @FXML
    private TextArea inputPreview;

    @FXML
    private TextArea outputPreview;

    static String inputString;
    static String outputString;

    @FXML
    public void exit()
    {
        System.exit(0);
    }

    @FXML
    public void loadFile()
    {
        Stage stage = Scribe.getPrimaryStage();
        final FileChooser fileChooser = new FileChooser();

        ExtensionFilter textFilter = new ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(textFilter);

        File inputFile = fileChooser.showOpenDialog(stage);

        if (inputFile != null)
        {
            /*
             * We just have to assume that the input is a valid text file if it ends
             * with .txt. We can handle non-ASCII input errors later.
             */
            boolean isText = inputFile.getName().toLowerCase().endsWith(".txt");

            if (isText)
            {
                try
                {
                    Stream<String> inputLines = Files.lines(inputFile.toPath(), StandardCharsets.US_ASCII);

                    StringBuilder builder = new StringBuilder();
                    boolean firstLine = true;

                    for (String lineIn : (Iterable<String>) inputLines::iterator)
                    {
                        if (firstLine)
                        {
                            builder.append(lineIn);
                            firstLine = false;
                        }
                        else
                        {
                            builder.append('\n' + lineIn);
                        }
                    }
                    inputString = builder.toString();
                    inputPreview.setText(inputString);
                    builder.delete(0, builder.length()-1);
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                    String fileNotFound_errorString = "The specified input file could not be found.";
                    Alert fileNotFound_alert = new Alert(Alert.AlertType.ERROR, fileNotFound_errorString);
                    fileNotFound_alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                    String IO_errorString = "The specified input file could not be read.";
                    Alert IO_alert = new Alert(Alert.AlertType.ERROR, IO_errorString);
                    IO_alert.showAndWait();
                }
            } else
            {
                String notText_errorString = "The specified input file is not a plaintext file.";
                Alert notText_alert = new Alert(Alert.AlertType.ERROR, notText_errorString);
                notText_alert.showAndWait();
            }
        }

    }

    // Use inputLines stream.
    @FXML
    public void formatFile()
    {
        outputString = FormatText.formatText();

        if (outputString != null)
        {
            outputPreview.setText(outputString);
            tabPane.getSelectionModel().select(1); // Select second tab (output tab)
        }
    }

    @FXML
    public void saveFile()
    {
        final FileChooser fileChooser = new FileChooser();

        ExtensionFilter textFilter = new ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(textFilter);

        File outputFile = fileChooser.showSaveDialog(Scribe.getPrimaryStage());

        if (outputFile != null)
        {
            try
            {
                PrintWriter writer = new PrintWriter(outputFile);
                writer.write(outputString);
                writer.close();
            } catch (IOException e)
            {
                e.printStackTrace();
                String IO_errorString = "The specified input file could not be written to.";
                Alert IO_alert = new Alert(Alert.AlertType.ERROR, IO_errorString);
                IO_alert.showAndWait();
            }
        }
    }

}
