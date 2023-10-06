import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ButtonClickApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Click Me");

        // Define an action when the button is clicked
        button.setOnAction(e -> {
            System.out.println("Button clicked!");
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Button Click Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
