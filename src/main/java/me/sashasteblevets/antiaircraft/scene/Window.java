package me.sashasteblevets.antiaircraft.scene;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import me.sashasteblevets.antiaircraft.main.Main;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Window {

    @FXML private GridPane rootLayout;
    @FXML private VBox menuLayout;
    @FXML private TextField speedTextField;
    @FXML private TextField widthTextField;
    @FXML private TextField heightTextField;
    @FXML private Canvas canvas;

    private GraphicsContext gc;

    private final AtomicInteger targetPosX = new AtomicInteger(200);
    private final AtomicInteger targetPosY = new AtomicInteger(50);
    private int targetWidth = 20;
    private int targetHeight = 20;
    private int targetSpeed = 10;

    @FXML
    private void initialize() {

        gc = this.canvas.getGraphicsContext2D();

        Main.getMain().getSchedulerUtil().scheduleAtFixedRate(() -> {

            final double x0 = 0;
            final double y0 = canvas.getHeight() - 50;

            targetPosX.addAndGet(targetSpeed);

            gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
            gc.fillRect(targetPosX.get(), targetPosY.get(), targetWidth, targetHeight);
            gc.fillRect(x0, y0, 70, 50);

            gc.setStroke(Color.BLACK);

            gc.beginPath();
            gc.moveTo(x0, y0);
            gc.lineTo(targetPosX.get(), targetPosY.get());
            // gc.lineTo(posX.get() - x0, y0);
            gc.closePath();
            gc.stroke();

            gc.beginPath();

        }, 1, 200, TimeUnit.MILLISECONDS);
    }

    @FXML
    private void buttonMouseClicked(MouseEvent event) {
        targetPosX.set(200);
        targetPosY.set(50);

        targetWidth = Integer.parseInt(widthTextField.getText());
        targetHeight = Integer.parseInt(heightTextField.getText());

        targetSpeed = Integer.parseInt(speedTextField.getText());
    }

}
