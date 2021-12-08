package com.shapeshifter.Actor.MovementStrategy;

import com.shapeshifter.Actor.Actor;
import com.shapeshifter.GameWorld;

public class FollowMovementStrategy implements MovementStrategy {

    @Override
    public float getNewSpeed(float curPosX, float curPosY, float curSpeed) {
        return 0;
    }

    @Override
    public float getNewAngularSpeed(float rotation, float curAngularSpeed) {
        return 0;
    }
}
