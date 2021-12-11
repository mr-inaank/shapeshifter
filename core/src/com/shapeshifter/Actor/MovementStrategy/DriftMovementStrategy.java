package com.shapeshifter.Actor.MovementStrategy;

import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.State.AttackingState;

public class DriftMovementStrategy implements MovementStrategy{

    private Actor source;

    public DriftMovementStrategy(Actor source) {
        this.source = source;
    }
    @Override
    public float getNewSpeed() {
        if (source.getState().getClass() == AttackingState.class) return source.getMaxSpeed(); //if charging, needs to go fast :/ (this is messy)
        return source.getSpeed();
    }

    @Override
    public double getNewAngularSpeed() {
        return 0;
    }
}
