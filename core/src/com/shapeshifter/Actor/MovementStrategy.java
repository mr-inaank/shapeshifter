package com.shapeshifter.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.shapeshifter.GameWorld;

public interface MovementStrategy {

    public float getNewSpeed(float curSpeed);
    public float getNewAngularSpeed(float curAngularSpeed);

}
