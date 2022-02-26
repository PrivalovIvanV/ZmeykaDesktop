package com.example.zmeykadesktop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class End {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button knopka;

    @FXML
    private Text score;

    @FXML
    void initialize() {
        score.setText(HelloApplication.resolt);
        knopka.setOnAction(actionEvent -> {
            Parent menuRoot = null;
            try {
                menuRoot = FXMLLoader.load(getClass().getResource("first.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(menuRoot, 366, 416);

            HelloApplication.DaTiChert.setScene(scene);

        });}

}