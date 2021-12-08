package com.shapeshifter.Actor.MovementStrategy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.shapeshifter.GameWorld;

public interface MovementStrategy {

    public float getNewSpeed(float curPosX, float curPosY, float curSpeed);
    public float getNewAngularSpeed(float rotation, float curAngularSpeed);

}
