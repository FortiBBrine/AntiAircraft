package me.sashasteblevets.antiaircraft.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import me.sashasteblevets.antiaircraft.utils.SchedulerUtil;

public class Main extends Application {

    private static Main INSTANCE;

    @Getter
    private final SchedulerUtil schedulerUtil = new SchedulerUtil(4);

    public Main() {
        INSTANCE = this;
    }

    public static Main getMain() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/window.fxml"));

        Parent root = fxmlLoader.load();
        root.getStylesheets().add("style/style.css");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("ВТЮІ 2023");
        primaryStage.show();

    }

    @Override
    public void stop() {
        this.schedulerUtil.shutdown();
    }
}
