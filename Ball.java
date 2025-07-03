/*
definition for a single Ball object
a Ball can be created on the drawing window and will move and "bounce"
each ball has a position (indicated by (x,y) coordinates of its center, a velocity (in x and y components),
a radius, and a color
 */

import java.awt.Color;
import java.util.Random;

public class Ball {
    //private instance variables that describe attributes of this Ball object
    private double centerX, centerY; //coordinates of the center of this Ball
    private double velocityX, velocityY; //x & y components of the velocity of this Ball
    private double radius;
    private Color ballColor;


    //TODO: create default constructor according to assignment instructions
    public  Ball() {
        Random rand = new Random();
        centerX = 0;
        centerY = 0;
        velocityX = rand.nextDouble(0.01, 0.03);
        velocityY = rand.nextDouble(0.01, 0.03);
        int red = rand.nextInt(0, 256);
        int green = rand.nextInt(0, 256);
        int blue = rand.nextInt(0, 256);
        Color randomColor = new Color(red, green, blue);
        ballColor = randomColor;
        radius = rand.nextDouble(0.005, 0.03);
    }
    public Ball(double cX, double cY, double vX, double vY, Color color, double r) {
        centerX = cX;
        centerY = cY;
        velocityX = vX;
        velocityY = vY;
        ballColor = color;
        radius = r;
    }

//TODO: create "getter" and "setter" methods according to assignment instructions
    public double getCenterX() {
        return centerX;
    }
    public double getCenterY () {
        return centerY;
    }
    public Color getBallColor() {
        return ballColor;
    }
    public double getVelocityX() {
        return velocityX;
    }
    public double getVelocityY() {
        return velocityY;
    }
    public double getRadius() {
        return radius;
    }

    //setter methods
    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }
    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }


}

