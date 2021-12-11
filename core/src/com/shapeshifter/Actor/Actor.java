package com.shapeshifter.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.State.State;
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

    //Behaviour
    private int faction;
    private State state;

    //RNG
    private static final Random rand = new Random();

    //Constructors
    public Actor() {
        this.posX = rand.nextInt(1920 * 5);
        this.posY = rand.nextInt(1080 * 5);

        this.speed = rand.nextInt(15) + 5;
        this.maxSpeed = 20;

        this.rotation = rand.nextInt(360) + 0.5f; //not whole number to avoid Math.tan failing when at 90 or 270
        this.angularSpeed = rand.nextInt(4) + 1;
        this.maxAngularSpeed = 5;
    }

    //Methods
    public void move() {
        if (speed > maxSpeed) speed = maxSpeed;
        if (angularSpeed > maxAngularSpeed) angularSpeed = maxAngularSpeed;
        if (angularSpeed < -maxAngularSpeed) angularSpeed = -maxAngularSpeed;

        posX += speed * Math.cos(this.rotation * (Math.PI / 180));
        posY += speed * Math.sin(this.rotation * (Math.PI / 180));
        rotation += angularSpeed;
    }

    public void updateState() {

        if (this == GameWorld.INSTANCE.player) return; //REMOVE THIS

        state.tick();
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

    //rename this (and maybe rename move to updatePos or something)
    public void aim() {

        angularSpeed = state.getMovestrat().getNewAngularSpeed();
        speed = state.getMovestrat().getNewSpeed();

    }


    //Getters
    public State getState() {
        return state;
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

    public float getAngularSpeed() {
        return angularSpeed;
    }

    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    public float getSpeed() {
        return speed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getFaction() {
        return faction;
    }


    //Setters
    public void setState(State state) {
        this.state = state;
    }

    public void setFaction(int faction) {
        this.faction = faction;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMaxAngularSpeed (int maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

}
