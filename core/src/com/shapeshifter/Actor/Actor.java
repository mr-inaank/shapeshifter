package com.shapeshifter.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.AttackState.AttackState;
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
    private MovementStrategy strategy;
    private AttackState state;

    //RNG
    private static final Random rand = new Random();

    //Constructors
    public Actor() {
        this.posX = rand.nextInt(1920 * 5);
        this.posY = rand.nextInt(1080 * 5);

        this.speed = 0;
        this.maxSpeed = rand.nextInt(15) + 5;

        this.rotation = rand.nextInt(360) + 0.5f; //not whole number to avoid Math.tan failing when at 90 or 270
        this.angularSpeed = 0;
        this.maxAngularSpeed = rand.nextInt(4) + 1;
    }

    //Methods
    public void move() {
        this.posX += speed * Math.cos(this.rotation * (Math.PI / 180));
        this.posY += speed * Math.sin(this.rotation * (Math.PI / 180));
        this.rotation += angularSpeed;
    }

    public void useSkill() {

        if (this == GameWorld.INSTANCE.player) return;

        state.tick();

        if (rand.nextInt(50) == 1) { //some condition that triggers the skill usage
            state.nextStage();
        }

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

        if (strategy != null) {
            angularSpeed = strategy.getNewAngularSpeed();
            speed = strategy.getNewSpeed();
        }
    }


    //Getters
    public AttackState getAttack() {
        return attack;
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


    //Setters
    public void setStrategy(MovementStrategy strategy) {
        this.strategy = strategy;
    }

    public void setAttack(AttackState attack) {
        this.attack = attack;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}
