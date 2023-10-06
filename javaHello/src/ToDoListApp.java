import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    private ObservableList<String> tasks = FXCollections.observableArrayList();
    private ListView<String> listView = new ListView<>(tasks);
    private TextField taskInput = new TextField();

    @Override
    public void start(Stage primaryStage) {
        listView.setPrefSize(250, 300);
        taskInput.setPrefWidth(150);

        Button addButton = new Button("Add Task");
        Button editButton = new Button("Edit Task");
        Button deleteButton = new Button("Delete Task");

        addButton.setOnAction(e -> addTask());
        editButton.setOnAction(e -> editTask());
        deleteButton.setOnAction(e -> deleteTask());

        HBox inputBox = new HBox(taskInput, addButton, editButton, deleteButton);
        VBox root = new VBox(listView, inputBox);

        Scene scene = new Scene(root, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("To-Do List App");
        primaryStage.show();
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            taskInput.clear();
        }
    }

    private void editTask() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String updatedTask = taskInput.getText().trim();
            if (!updatedTask.isEmpty()) {
                tasks.set(selectedIndex, updatedTask);
                taskInput.clear();
            }
        }
    }

    private void deleteTask() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tasks.remove(selectedIndex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
