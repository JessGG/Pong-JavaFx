package com.example.pongjess;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Juego extends BorderPane {
    private StackPane pista;
    private Rectangle leftWall;
    private Rectangle rightWall;
    private Rectangle topWall;
    private Rectangle bottomWall;
    private JuegoController Controlador;
    private Circle ball;
    private Rectangle pinkPlayer;
    private Rectangle bluePlayer;
    private Text pinkPoints;
    private Text bluePoints;
    private int puntos;

    public Juego () {
        this.pista = new StackPane();
        this.leftWall = new Rectangle();
        this.rightWall = new Rectangle();
        this.topWall = new Rectangle();
        this.bottomWall = new Rectangle();
        this.ball = new Circle();
        this.pinkPlayer = new Rectangle();
        this.bluePlayer = new Rectangle();
        this.bluePoints = new Text();
        this.pinkPoints = new Text();

        JuegoController controlador = new JuegoController(bluePoints, pinkPoints, leftWall, rightWall, bottomWall, topWall, ball, pinkPlayer, bluePlayer, pista);

        //PLAYER 1

        pinkPlayer.setFill(Color.LIGHTPINK);
        pinkPlayer.heightProperty().bind(leftWall.widthProperty().multiply(5));
        pinkPlayer.widthProperty().bind(leftWall.widthProperty());

        //PLAYER 2
        bluePlayer.setFill(Color.LIGHTBLUE);
        bluePlayer.heightProperty().bind(leftWall.widthProperty().multiply(5));
        bluePlayer.widthProperty().bind(leftWall.widthProperty());

        //PARED IZQUIERDA
        leftWall.setFill(Color.BLACK);
        leftWall.widthProperty().bind(pista.widthProperty().divide(30));
        leftWall.heightProperty().bind(pista.heightProperty());

        //PARED DERECHA
        rightWall.widthProperty().bind(pista.widthProperty().divide(30));
        rightWall.heightProperty().bind(pista.heightProperty());
        rightWall.setFill(Color.BLACK);

        //PARED SUPERIOR
        topWall.setFill(Color.BLACK);
        topWall.heightProperty().bind(pista.heightProperty().divide(30));
        topWall.widthProperty().bind(pista.widthProperty());

        //PARED INFERIOR
        bottomWall.setFill(Color.BLACK);
        bottomWall.heightProperty().bind(pista.heightProperty().divide(30));
        bottomWall.widthProperty().bind(pista.widthProperty());

        //BALL
        ball.setFill(Color.LIGHTPINK);
        ball.radiusProperty().bind(leftWall.widthProperty().divide(2));

        //POINTS

        pinkPoints.setText("Jugador 1 : ");
        pinkPoints.setFont(new Font("", 15));
        pinkPoints.setFill(Color.LIGHTPINK);
        bluePoints.setText("Jugador 2 : ");
        bluePoints.setFont(new Font("", 15));
        bluePoints.setFill(Color.LIGHTBLUE);

        pinkPoints.translateXProperty().bind(leftWall.widthProperty());
        bluePoints.translateXProperty().bind(rightWall.widthProperty().multiply(-1));

        pista.getChildren().addAll(leftWall,rightWall, topWall, bottomWall, ball, pinkPlayer, bluePlayer, bluePoints, pinkPoints);
        pista.setAlignment(pinkPlayer, Pos.CENTER_LEFT );
        pista.setAlignment(ball, Pos.CENTER);
        pista.setAlignment(bluePlayer, Pos.CENTER_RIGHT);
        pista.setAlignment(leftWall, Pos.CENTER_LEFT);
        pista.setAlignment(rightWall, Pos.CENTER_RIGHT);
        pista.setAlignment(bottomWall, Pos.BOTTOM_CENTER);
        pista.setAlignment(topWall, Pos.TOP_CENTER);
        pista.setAlignment(pinkPoints, Pos.BOTTOM_LEFT);
        pista.setAlignment(bluePoints, Pos.BOTTOM_RIGHT);

        pinkPlayer.translateXProperty().bind(leftWall.widthProperty());
        bluePlayer.translateXProperty().bind(rightWall.widthProperty().multiply(-1));

        this.setCenter(pista);

    }

}

