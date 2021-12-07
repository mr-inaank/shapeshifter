package com.shapeshifter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import static java.lang.Math.tan;

public class Actor {

    private float posX;
    private float posY;
    private float speed;
    private float rotation;
    private Texture texture;

    Random rand = new Random();

    public Actor() {
        this.texture = new Texture("triangle.png");
        this.posX = rand.nextInt(1920*5);
        this.posY = rand.nextInt(1080*5);
        this.speed = rand.nextInt(15) + 5;
        this.rotation = rand.nextInt(360) + 0.5f;
    }

    public void move() {
        this.posX += speed * Math.cos(this.rotation * (Math.PI / 180));
        this.posY += speed * Math.sin(this.rotation * (Math.PI / 180));
    }

    public void collide() {
        if (posX > 1920*5 || posX < 0)  {this.rotation = 180 - this.rotation;}
        if (posY > 1080*5 || posY < 0) {this.rotation = -this.rotation;}
    }

    public void userControl() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) this.rotation += 3;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) this.rotation -= 3;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) this.speed += 0.2;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) this.speed -= 0.2;
    }

    public void aim(Actor target) {
        if (this == GameWorld.INSTANCE.player) return;

        float heading = rotation % 360;

        if (target.getPosY() < (tan(this.rotation * (Math.PI / 180)) * target.getPosX() + (this.posY - this.posX * tan(this.rotation * (Math.PI / 180))))) { //y < mx + b < y
            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) rotation += 3;
            else rotation -= 3;
        }
        else {
            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) rotation -= 3;
            else rotation += 3;
        }
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
