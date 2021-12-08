package com.shapeshifter.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.GameWorld;

import java.util.Random;

public abstract class Actor {


    protected float posX;
    protected float posY;
    protected float speed;
    protected float rotation;
    protected float angularSpeed;
    protected Texture texture;

    Random rand = new Random();

    public Actor() {
        this.posX = rand.nextInt(1920*5);
        this.posY = rand.nextInt(1080*5);
        this.speed = rand.nextInt(15) + 5;
        this.angularSpeed = rand.nextInt(4) + 1;
        this.rotation = rand.nextInt(360) + 0.5f; //not whole number to avoid Math.tan failing when at 90 or 270
    }

    public void move() {
        this.posX += speed * Math.cos(this.rotation * (Math.PI / 180));
        this.posY += speed * Math.sin(this.rotation * (Math.PI / 180));
    }

    //should this be here or somewhere else?
    public void collide() {
        if (posX > 1920*5 || posX < 0)  {this.rotation = 180 - this.rotation;}
        if (posY > 1080*5 || posY < 0) {this.rotation = -this.rotation;}
    }



    //this is a strategy that should override aim() for this actor
    public void userControl() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) this.rotation += angularSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) this.rotation -= angularSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) this.speed += 0.2;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) this.speed -= 0.2;
    }

    //aim strategy when following a target
    public void aim(Actor target) {
        if (this == GameWorld.INSTANCE.player) return;

        float heading = rotation % 360;

        if (target.getPosY() < (Math.tan(this.rotation * (Math.PI / 180)) * target.getPosX() + (this.posY - this.posX * Math.tan(this.rotation * (Math.PI / 180))))) { //y < mx + b < y
            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) rotation += angularSpeed;
            else rotation -= angularSpeed;
        }
        else {
            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) rotation -= angularSpeed;
            else rotation += angularSpeed;
        }
    }


    //Getters
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
