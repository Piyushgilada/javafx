import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class DocumentEditorApp extends Application {

    private Stage primaryStage;
    private TextArea textArea = new TextArea();
    private File currentFile = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Document Editor");

        BorderPane root = new BorderPane();

        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        textArea.setWrapText(true);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem saveAsMenuItem = new MenuItem("Save As");
        MenuItem exitMenuItem = new MenuItem("Exit");

        fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, new SeparatorMenuItem(), exitMenuItem);

        newMenuItem.setOnAction(event -> {
            if (isDocumentModified()) {
                askToSaveChanges();
            }
            textArea.clear();
            currentFile = null;
        });

        openMenuItem.setOnAction(event -> {
            if (isDocumentModified()) {
                askToSaveChanges();
            }
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                try {
                    String content = new String(Files.readAllBytes(Paths.get(selectedFile.toURI())));
                    textArea.setText(content);
                    currentFile = selectedFile;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        saveMenuItem.setOnAction(event -> saveDocument());

        saveAsMenuItem.setOnAction(event -> saveDocumentAs());

        exitMenuItem.setOnAction(event -> {
            if (isDocumentModified()) {
                askToSaveChanges();
            }
            primaryStage.close();
        });

        menuBar.getMenus().addAll(fileMenu);
        return menuBar;
    }

    private boolean isDocumentModified() {
        if (currentFile == null) {
            return !textArea.getText().isEmpty();
        } else {
            try {
                String content = new String(Files.readAllBytes(Paths.get(currentFile.toURI())));
                return !textArea.getText().equals(content);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private void askToSaveChanges() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save Changes");
        alert.setHeaderText("Do you want to save your changes?");
        alert.setContentText("Choose your option.");

        ButtonType saveButton = new ButtonType("Save");
        ButtonType discardButton = new ButtonType("Discard Changes");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(saveButton, discardButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == saveButton) {
                saveDocument();
            } else if (result.get() == discardButton) {
                // Discard changes
            } else {
                // Cancel
            }
        }
    }

    private void saveDocument() {
        if (currentFile != null) {
            try {
                FileWriter writer = new FileWriter(currentFile);
                writer.write(textArea.getText());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveDocumentAs();
        }
    }

    private void saveDocumentAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(primaryStage);
        if (selectedFile != null) {
            try {
                FileWriter writer = new FileWriter(selectedFile);
                writer.write(textArea.getText());
                writer.close();
                currentFile = selectedFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
