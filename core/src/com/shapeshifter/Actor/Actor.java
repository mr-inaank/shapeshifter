package com.shapeshifter.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.MovementStrategy.MovementStrategy;
import com.shapeshifter.GameWorld;

import java.util.Random;

public abstract class Actor {


    protected float posX;
    protected float posY;
    protected float speed;
    protected float maxSpeed;
    protected float rotation;
    protected float angularSpeed;
    protected float maxAngularSpeed;
    protected Texture texture;

    private MovementStrategy strategy;

    private Random rand = new Random();

    public Actor() {
        this.posX = rand.nextInt(1920 * 5);
        this.posY = rand.nextInt(1080 * 5);
        this.maxSpeed = rand.nextInt(15) + 5;
        this.speed = 0;
        this.maxAngularSpeed = rand.nextInt(4) + 1;
        this.angularSpeed = 0;
        this.rotation = rand.nextInt(360) + 0.5f; //not whole number to avoid Math.tan failing when at 90 or 270
    }

    public void setStrategy(MovementStrategy strategy) {
        this.strategy = strategy;
    }

    public void move() {
        this.posX += speed * Math.cos(this.rotation * (Math.PI / 180));
        this.posY += speed * Math.sin(this.rotation * (Math.PI / 180));


        if (this == GameWorld.INSTANCE.player) return;

        this.rotation += angularSpeed;
    }

    //should this be here or somewhere else?
    public void collide() {
        if (posX > 1920 * 5 || posX < 0) {
            this.rotation = 180 - this.rotation;
        }
        if (posY > 1080 * 5 || posY < 0) {
            this.rotation = -this.rotation;
        }
    }

    //this is a strategy that should override aim() for this actor when being controlled by user input
    public void userControl() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) this.rotation += maxAngularSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) this.rotation -= maxAngularSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) this.speed += 0.2;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) this.speed -= 0.2;
    }

    //aim strategy when following a target
    public void aim(Actor target) {
        if (this == GameWorld.INSTANCE.player) return;

//        float heading = rotation % 360;
//
//        if (target.getPosY() < (Math.tan(this.rotation * (Math.PI / 180)) * target.getPosX() + (this.posY - this.posX * Math.tan(this.rotation * (Math.PI / 180))))) { //y < mx + b < y
//            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) rotation += angularSpeed;
//            else rotation -= angularSpeed;
//        }
//        else {
//            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) rotation -= angularSpeed;
//            else rotation += angularSpeed;
//        }

        if (strategy != null) {
            angularSpeed = strategy.getNewAngularSpeed();
            speed = strategy.getNewSpeed();
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

    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    public float getMaxSpeed() {
        return maxAngularSpeed;
    }

    public Texture getTexture() {
        return texture;
    }


}
