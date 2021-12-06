package com.shapeshifter;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Actor {

    private int posX;
    private int posY;
    private int speed;
    private float rotation;
    private Texture texture = new Texture("triangle.png");

    Random rand = new Random();

    public Actor() {
        this.posX = rand.nextInt(100000);
        this.posY = rand.nextInt(100000);
        this.speed = rand.nextInt(1000);
        this.rotation = rand.nextInt(360);
    }

    public void move() {
        this.posX += speed * Math.cos(this.rotation * (Math.PI / 180));
        this.posY += speed * Math.sin(this.rotation * (Math.PI / 180));
    }

    public void aim() {
        this.rotation += 3;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public float getRotation() {
        return rotation;
    }

    public Texture getTexture() {
        return texture;
    }


}
