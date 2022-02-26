package com.example.zmeykadesktop;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

//import java.awt.*;
//import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Repaint {
    private final int SIZE = 320;
    private int SCORE = 0;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int lenght;
    private boolean left;
    private boolean right = true;
    private boolean up;
    private boolean down;
    static boolean inGame = true;
    private GraphicsContext gc;


    public Repaint(GraphicsContext gc) {
        initGame();
        this.gc = gc;



        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                BasePaint(); //отрисовывает игровое поле
                if (inGame){
                    move(); // модифицирует массив координат
                    Zmeyka();//отрисовка змейки
                    pravila(); //логика игры
                }else {
                    Platform.runLater(() -> {
                        HelloApplication.resolt = counter(SCORE);
                        timer.cancel();
                        timer.purge();
                        Parent menuRoot = null;
                        try {
                            menuRoot = FXMLLoader.load(getClass().getResource("end.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Scene scene = new Scene(menuRoot, 366, 416);

                        HelloApplication.DaTiChert.setScene(scene);
                    });}
                dddd(); //обработчик событий с клавиатуры


            }
        };

        timer.schedule(timerTask, 0, 200);

    }


    public void initGame() {
        lenght = 3;
        inGame = true;
        SCORE = 0;
        for (int i = 0; i < lenght; i++) {
            x[i] = 160 - i*DOT_SIZE;
            y[i] = 160;
        }
        CreaateApple();
    }


    public void CreaateApple(){

        while (true) {

            int finalXui = ((int) (Math.random() * 19)) * DOT_SIZE;
            int finalYog = ((int) (Math.random() * 19)) * DOT_SIZE;

            boolean ar1 = Arrays.stream(x)
                    .noneMatch(peremennaya -> finalXui == peremennaya);

            boolean ar2 = Arrays.stream(y)
                    .noneMatch(peremennaya -> finalYog == peremennaya);


            if ( ar1 & ar2 ) {
                appleX = finalXui;
                appleY = finalYog;
                break;
            }

        }

    }


    private void BasePaint() {
        for (int i = 0; i < SIZE + 1; i += 2 * DOT_SIZE) {
            for (int j = 0; j < SIZE+1; j += 2 * DOT_SIZE) {
                gc.setFill(javafx.scene.paint.Color.rgb(0, 100, 0));
                gc.fillRect(i, j, 16, 16);
                gc.setFill(javafx.scene.paint.Color.rgb(50, 205, 50));
                gc.fillRect(i, j - DOT_SIZE, 16, 16);

            }

        }
        for (int i = DOT_SIZE; i < SIZE+ 1; i += 2 * DOT_SIZE) {
            for (int j = DOT_SIZE; j < SIZE; j += 2 * DOT_SIZE) {
                gc.setFill(javafx.scene.paint.Color.rgb(0, 128, 0));
                gc.fillRect(i, j, 16, 16);
                gc.setFill(javafx.scene.paint.Color.rgb(50, 205, 50));
                gc.fillRect(i , j - DOT_SIZE, 16, 16);
            }

        }
    }


    private void Zmeyka(){

        if (inGame) {
            gc.setFill(Color.rgb(255, 0, 0));
            gc.fillOval(appleX, appleY, 16, 16);

            for (int i = 0; i < lenght; i++) {
                gc.setFill(Color.rgb(105, 105, 105));
                gc.fillRect(x[i], y[i], 16, 16);

            }
            if (right){
                gc.setFill(Color.rgb(255, 255, 255));
                gc.fillOval(x[0]+12, y[0]+3, 2,2);
                gc.fillOval(x[0]+12, y[0]+11, 2,2);
            }
            if (left){
                gc.setFill(Color.rgb(255, 255, 255));
                gc.fillOval(x[0]+2, y[0]+3, 2,2);
                gc.fillOval(x[0]+2, y[0]+11, 2,2);
            }
            if (up){
                gc.setFill(Color.rgb(255, 255, 255));
                gc.fillOval(x[0]+3, y[0]+2, 2,2);
                gc.fillOval(x[0]+11, y[0]+2, 2,2);
            }
            if (down){
                gc.setFill(Color.rgb(255, 255, 255));
                gc.fillOval(x[0]+3, y[0]+12, 2,2);
                gc.fillOval(x[0]+11, y[0]+12, 2,2);
            }
        } else {
//            String str = "Game Over";
//            Font f = new Font("Aria", Font.BOLD, 48);
//            gc.setFill(javafx.scene.paint.Color.rgb(0, 100, 0));
//            gc.setFont(f);
//            gc.drawString(str, 25, SIZE/2);
        }



    }


    public void move(){
        for (int i = lenght; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if (left) {
            x[0] -= DOT_SIZE;
            if (x[0] == -DOT_SIZE) {x[0] = 320; }

        }
        if (right) {
            x[0] += DOT_SIZE;
            if (x[0] == 320) {x[0] = 0;}
        }
        if (up) {
            y[0] -= DOT_SIZE;
            if (y[0] == (-DOT_SIZE)) {y[0] = 320; }

        }
        if (down) {
            y[0] += DOT_SIZE;
            if (y[0] == 320) {y[0] = 0; }

        }
    }


    private void pravila(){
        if ((x[0] == appleX) & (y[0] == appleY)){
            lenght++;
            CreaateApple();
            SCORE++;
            First.score1.setText(counter(SCORE));
        }
        for (int i = lenght; i >3 ; i--) {
            if ((x[0] == x[i]) & (y[0] == y[i])){
                inGame = false;
                lenght = 3;
            }
        }
    }


    public void dddd(){

        HelloApplication.DaTiChert.getScene().setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()){
                case UP -> { if (!down){
                    left = false;
                    right = false;
                    up = true;}}

                case DOWN -> { if (!up){
                    left = false;
                    right = false;
                    down = true;}}

                case RIGHT -> { if (!left){
                    right = true;
                    up = false;
                    down = false;}}

                case LEFT -> {if (!right){
                    left = true;
                    up = false;
                    down = false;}}

                default ->
                        System.out.println("Ты ебаный гей");
            }


        });

    }

    private String counter(int scr){
        LinkedList<Integer> list = new LinkedList<>();
        String str = Integer.toString(scr);

        for (int i1 =0;  i1 < str.length() ; i1++){
            list.add(Integer.parseInt(String.valueOf(str.charAt(i1))));
        }//записывает число в массив почисленно

        if (str.length() < 6){
            for (int i = 0; i < (6 - str.length()); i++){
                list.addFirst(0);
            }
        }//добавляет нули в начало списка

        str = Integer.toString(list.getFirst());

        for (int k = 1; k < list.size(); k++){
            str += Integer.toString(list.get(k));
        }//переводит массив в строку

        return str;
    }

}
