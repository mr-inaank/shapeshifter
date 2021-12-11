package com.shapeshifter.Actor.MovementStrategy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.shapeshifter.Actor.Actor;

public class FollowMovementStrategy implements MovementStrategy {

    private final Actor source;
    private final Actor target;

    public FollowMovementStrategy(Actor source, Actor target) {
        this.source = source;
        this.target = target;

    }

    @Override
    public float getNewSpeed() {
        return source.getSpeed();
    }

    @Override
    public double getNewAngularSpeed() {
        double heading = source.getRotation() % 360;
        double posHeading = heading;
        if (heading < 0) posHeading = 360 - Math.abs(heading);
        double desired = Math.atan((target.getPosY()-source.getPosY())/(target.getPosX() - source.getPosX())) * (180 / Math.PI);

        //if 1st or 4th quadrant, then error doesn't need adjusting
        //check for 2nd and 3rd quadrant
        if (target.getPosX() - source.getPosX() < 0) {
            if (target.getPosY() - source.getPosY() > 0) {
                //2nd quadrant
                desired = 180 - Math.abs(desired);
            }
            else {
                //3rd quadrant
                desired = 180 + desired;
            }
        }
        if (desired < 0) desired = 360 - Math.abs(desired);

        double error = Math.abs(desired - posHeading);
        if (error > 180) error = 360 - error;

        if (target.getPosY() < (Math.tan(source.getRotation() * (Math.PI / 180)) * target.getPosX() + (source.getPosY() - source.getPosX() * Math.tan(source.getRotation() * (Math.PI / 180))))) { //y < mx + b
            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) {
                return error/2;
                //return source.getMaxAngularSpeed();
            } else {
                return -error/2;
                //return -source.getMaxAngularSpeed();
            }
        } else {
            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) {
                return -error/2;
                //return -source.getMaxAngularSpeed();
            } else {
                return error/2;
                //return source.getMaxAngularSpeed();
            }
        }
    }
}
