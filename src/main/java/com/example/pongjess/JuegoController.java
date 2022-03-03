package com.example.pongjess;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Stack;

public class JuegoController {
    private Rectangle pinkPlayer;
    private Rectangle bluePlayer;
    private Rectangle leftWall;
    private Rectangle rightWall;
    private Rectangle topWall;
    private Rectangle bottomWall;

    private Circle ball;
    private StackPane pista;
    private Timeline animacion;
    private IntegerProperty blueScore;
    private IntegerProperty pinkScore;

    private double movBallY;
    private double movBallX;
    private double movPalaRosa;
    private double movPalaAzul;
    private Text bluePoints;
    private Text pinkPoints;

    public JuegoController(Text bluePoints, Text pinkPoints, Rectangle leftWall,
                           Rectangle rightWall, Rectangle bottomWall,
                           Rectangle topWall, Circle ball, Rectangle pinkPlayer,
                           Rectangle bluePlayer, StackPane pista) {
        this.leftWall = leftWall;
        this.rightWall = rightWall;
        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.ball = ball;
        this.pista = pista;
        this.pinkPlayer = pinkPlayer;
        this.bluePlayer = bluePlayer;
        this.movBallX = 3;
        this.movBallY = 3;
       this.bluePoints = bluePoints;
       this.pinkPoints = pinkPoints;

        blueScore = new SimpleIntegerProperty(0);
        pinkScore = new SimpleIntegerProperty(0);

        inicializarJuego();
        inicializarControles();
    }

    private void initmove(){
        moverBall();
        movPinkPlayer();
        movBluePlayer();

    }

    private void movPinkPlayer() {
        controles();
        pinkPlayer.setTranslateY(pinkPlayer.getTranslateY()+movPalaRosa);
    }
    private void movBluePlayer(){
        controles();
        bluePlayer.setTranslateY(bluePlayer.getTranslateY()+movPalaAzul);
    }

    private void controles() {
        pista.setOnKeyPressed(e->{
            // System.out.println(e.getCode());
            switch (e.getCode()){
                case ENTER: animacion.play();
                break;
                case W: movPalaRosa = -4;
                    break;
                case S: movPalaRosa= 4;
                    break;
                case DOWN:
                    movPalaAzul = 4;
                    break;
                case UP:
                    movPalaAzul=-4;
                    break;
            }
        });
        pista.setOnKeyReleased(e->{
            switch (e.getCode()){
                case W:
                case S: movPalaRosa=0;
                break;
                case UP:
                case DOWN: movPalaAzul=0;
            }
        });

    }

    private void inicializarControles(){
        pista.setOnKeyPressed(e-> animacion.play());
        pista.setFocusTraversable(true);

    }

    private void inicializarJuego() {
        this.animacion = new Timeline(new KeyFrame(Duration.millis(17), t->{
            detectarColision();
            initmove();
        }));
        animacion.setCycleCount(Animation.INDEFINITE);
    }

    private void detectarColision() {

        /// COLISION DE PELOTA CONTRA LAS PAREDES Y LOS JUGADORES

        if (ball.getBoundsInParent().intersects(topWall.getBoundsInParent()) ||
                ball.getBoundsInParent().intersects(bottomWall.getBoundsInParent())){
            movBallY = -movBallY;
        }
        if (ball.getBoundsInParent().intersects(pinkPlayer.getBoundsInParent()) ||
                ball.getBoundsInParent().intersects(bluePlayer.getBoundsInParent())){
            movBallX = - movBallX;

        }
        /// SUMA DE PUNTOS AL CRUZAR LA PORTERIA CONTRARIA

        if (ball.getBoundsInParent().intersects(rightWall.getBoundsInParent())){
            pinkScore.setValue(pinkScore.getValue()+1);
            pinkPoints.textProperty().bind(pinkScore.asString("Jugador 1 : %d"));
            stopBall();
        }
        if (ball.getBoundsInParent().intersects(leftWall.getBoundsInParent())){
            blueScore.setValue(blueScore.getValue()+1);
            bluePoints.textProperty().bind(blueScore.asString("Jugador 2 : %d"));
            stopBall();
        }

        // PARA AL JUGADOR CUANDO CHOCA CONTRA LAS PAREDES SUPERIOR E INFERIOR

        if (pinkPlayer.getBoundsInParent().intersects(topWall.getBoundsInParent())){
            pinkPlayer.setTranslateY(pinkPlayer.getTranslateY()+4);
        }
        if (pinkPlayer.getBoundsInParent().intersects(bottomWall.getBoundsInParent())){
            pinkPlayer.setTranslateY(pinkPlayer.getTranslateY()-4);
        }
        if (bluePlayer.getBoundsInParent().intersects(topWall.getBoundsInParent())){
            bluePlayer.setTranslateY(bluePlayer.getTranslateY()+4);
        }
        if (bluePlayer.getBoundsInParent().intersects(bottomWall.getBoundsInParent())){
            bluePlayer.setTranslateY(bluePlayer.getTranslateY()-4);
        }
    }
    public  void moverBall(){
        ball.setTranslateX(ball.getTranslateX()+movBallX);
        ball.setTranslateY(ball.getTranslateY()+ movBallY);
    }

    public void stopBall(){
        animacion.stop();
        ball.setTranslateX(0);
        ball.setTranslateY(0);
    }
}
