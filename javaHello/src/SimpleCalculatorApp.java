import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimpleCalculatorApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextField inputField = new TextField();
        Button addButton = new Button("+");
        Button subtractButton = new Button("-");
        Button calculateButton = new Button("Calculate");
        TextField resultField = new TextField();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(inputField, 0, 0);
        grid.add(addButton, 1, 0);
        grid.add(subtractButton, 2, 0);
        grid.add(calculateButton, 0, 1);
        grid.add(resultField, 1, 1);

        addButton.setOnAction(e -> {
            // Perform addition logic here
        });

        subtractButton.setOnAction(e -> {
            // Perform subtraction logic here
        });

        calculateButton.setOnAction(e -> {
            // Perform calculation logic here and display the result in resultField
        });

        Scene scene = new Scene(grid, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple Calculator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
