package com.shapeshifter;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Actor {

    private float posX;
    private float posY;
    private int speed;
    private float rotation;
    private Texture texture;

    Random rand = new Random();

    public Actor() {
        this.texture = new Texture("triangle.png");
        this.posX = rand.nextInt(1000);
        this.posY = rand.nextInt(1000);
        this.speed = rand.nextInt(20);
        this.rotation = rand.nextInt(360);
    }

    public void move() {
        this.posX += speed * Math.cos(this.rotation * (Math.PI / 180));
        this.posY += speed * Math.sin(this.rotation * (Math.PI / 180));
    }

    public void aim() {
        if(posX > 1000 || posX < 0 || posY > 1000 || posY < 0) this.rotation += 2;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getRotation() {
        return rotation;
    }

    public Texture getTexture() {
        return texture;
    }


}
