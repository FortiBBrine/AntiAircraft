package me.sashasteblevets.antiaircraft.scene;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import me.sashasteblevets.antiaircraft.main.Main;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Window {

    @FXML
    private BorderPane rootLayout;

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    @FXML
    private void initialize() {
        this.canvas.heightProperty().bind(rootLayout.heightProperty());
        this.canvas.widthProperty().bind(rootLayout.widthProperty());

        AtomicInteger posX = new AtomicInteger(200);
        AtomicInteger posY = new AtomicInteger(50);

        gc = this.canvas.getGraphicsContext2D();

        final int v0 = 75;
//        final double angle = Math.toRadians(75);
        final double g = 9.81;
        final double dt = 0.01;

        Main.getMain().getSchedulerUtil().scheduleAtFixedRate(() -> {

            final double x0 = 0;
            final double y0 = canvas.getHeight() - 50;

            posX.addAndGet(10);
            if (posX.get() >= this.canvas.getWidth()) posX.set(0);

            gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
            gc.fillOval(posX.get(), posY.get(), 20, 20);
            gc.fillRect(x0, y0, 70, 50);

            gc.setStroke(Color.BLACK);

            double c = Math.sqrt(Math.pow(posX.get() - x0, 2) + Math.pow(posY.get() - y0, 2));
            double a = y0 - posY.get();

            double angle = /*Math.toDegrees*/(Math.asin(a / c));

//            System.out.println(angle);

            gc.beginPath();
            gc.moveTo(x0, y0);
            gc.lineTo(posX.get(), posY.get());
            gc.lineTo(posX.get() - x0, y0);
            gc.closePath();
            gc.stroke();

            gc.beginPath();

            double t = 0;

            gc.moveTo(x0, y0);

            while (true) {

                double x = x0 + v0 * Math.cos(angle) * t;
                double y = y0 - v0 * Math.sin(angle) * t + 0.5 * g * t * t;

                gc.lineTo(x, y);

                t += dt;

                if (y > 600) {
                    break;
                }
            }

            gc.stroke();


        }, 1, 200, TimeUnit.MILLISECONDS);
    }

}
