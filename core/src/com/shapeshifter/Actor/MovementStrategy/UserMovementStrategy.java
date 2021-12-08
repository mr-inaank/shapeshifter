package com.shapeshifter.Actor.MovementStrategy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.shapeshifter.Actor.Actor;

public class UserMovementStrategy implements MovementStrategy{

    private final Actor source;

    public UserMovementStrategy (Actor source) {
        this.source = source;
    }

    @Override
    public float getNewSpeed() {
        if (source.getSpeed() == source.getMaxSpeed()) return source.getMaxSpeed();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) return source.getSpeed() + 0.2f;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) return source.getSpeed() - 0.2f;
        return source.getSpeed();
    }

    @Override
    public float getNewAngularSpeed() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) return source.getMaxAngularSpeed();
        if (Gdx.input.isKeyPressed(Input.Keys.D)) return -source.getMaxAngularSpeed();
        return 0;
    }
}

