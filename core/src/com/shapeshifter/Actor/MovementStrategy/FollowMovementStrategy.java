package com.shapeshifter.Actor.MovementStrategy;

import com.shapeshifter.Actor.Actor;

public class FollowMovementStrategy implements MovementStrategy {

    private final Actor source;
    private final Actor target;

    public FollowMovementStrategy(Actor source, Actor target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public float getNewSpeed() {return source.getSpeed();}

    @Override
    public float getNewAngularSpeed() {
        float heading = source.getRotation() % 360;
        if (target.getPosY() < (Math.tan(source.getRotation() * (Math.PI / 180)) * target.getPosX() + (source.getPosY() - source.getPosX() * Math.tan(source.getRotation() * (Math.PI / 180))))) { //y < mx + b
            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) {
                return source.getMaxAngularSpeed();
            } else {
                return -source.getMaxAngularSpeed();
            }
        } else {
            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) {
                return -source.getMaxAngularSpeed();
            } else {
                return source.getMaxAngularSpeed();
            }
        }
    }
}
