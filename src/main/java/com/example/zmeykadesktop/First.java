package com.example.zmeykadesktop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;

public class First {

    static Text score1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Canvas canvas;

    @FXML
    private Text score;

    @FXML
    void initialize() {
        score1 = score;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        new Repaint(gc);




    }

}
