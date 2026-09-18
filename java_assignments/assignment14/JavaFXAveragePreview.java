package java_assignments.assignment14;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXAveragePreview extends Application {

    private static double average;
    private static Stage stage;
    private static boolean started = false;

    public static void showAverage(double value) {

        average = value;

        if (!started) {
            started = true;

            Thread javafxThread = new Thread(() -> {
                Application.launch(JavaFXAveragePreview.class);
            });

            javafxThread.setDaemon(true);
            javafxThread.start();

        } else {
            Platform.runLater(JavaFXAveragePreview::updateWindow);
        }
    }

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;
        updateWindow();
    }

    private static void updateWindow() {

        if (stage == null) {
            return;
        }

        Label title = new Label("Student Marks Viewer");

        Label averageLabel = new Label(
                String.format("Class Average: %.2f", average)
        );

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        VBox root = new VBox(
                15,
                title,
                averageLabel,
                closeButton
        );

        root.setStyle(
                "-fx-padding: 30; -fx-alignment: center;"
        );

        Scene scene = new Scene(root, 350, 200);

        stage.setTitle("Class Average - JavaFX");
        stage.setScene(scene);
        stage.show();
    }
}
