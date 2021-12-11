package com.shapeshifter.Actor.MovementStrategy;

import com.shapeshifter.Actor.Actor;

public class DriftMovementStrategy implements MovementStrategy{

    private Actor source;

    public DriftMovementStrategy(Actor source) {
        this.source = source;
    }

    @Override
    public float getNewSpeed() {
        return source.getSpeed();
    }

    @Override
    public float getNewAngularSpeed() {
        return 0;
    }
}
