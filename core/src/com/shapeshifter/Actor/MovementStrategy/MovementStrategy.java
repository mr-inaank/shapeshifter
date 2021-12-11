package com.shapeshifter.Actor.MovementStrategy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.shapeshifter.Actor.Actor;
import com.shapeshifter.GameWorld;

public interface MovementStrategy {

    public float getNewSpeed();
    public double getNewAngularSpeed();

}
