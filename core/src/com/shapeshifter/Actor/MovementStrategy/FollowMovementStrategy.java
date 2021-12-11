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
    public float getNewSpeed() {
        return source.getSpeed();
    }

    @Override
    public float getNewAngularSpeed() {
        float heading = source.getRotation() % 360;
        double desired = Math.atan((target.getPosY()-source.getPosY())/(target.getPosX() - source.getPosX()));
        //if 1st or 4th quadrant, then error doesn't need adjusting
        //check for 2nd and 3rd quadrant
        if (target.getPosX() - source.getPosX() <= 0) {
            if (target.getPosY() - source.getPosY() >= 0) {
                //2nd quadrant
                desired = 180 - desired;
            }
            else {
                //3rd quadrant
                desired = 180 + desired;
            }
        }

        float error = Math.abs((float)desired - heading);
        if (error > 180) error = 360 - error;
        //System.out.print("\nHeading: "); System.out.print(heading);
        //System.out.print("\nDesired: "); System.out.print(desired);
        //System.out.print("\nError: "); System.out.print(error);
        //FIX THIS

        if (target.getPosY() < (Math.tan(source.getRotation() * (Math.PI / 180)) * target.getPosX() + (source.getPosY() - source.getPosX() * Math.tan(source.getRotation() * (Math.PI / 180))))) { //y < mx + b
            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) {
                return error;
                //return source.getMaxAngularSpeed();
            } else {
                return -error;
                //return -source.getMaxAngularSpeed();
            }
        } else {
            if ((heading > 90 && heading < 270) || (heading < -90 && heading > -270)) {
                return -error;
                //return -source.getMaxAngularSpeed();
            } else {
                return error;
                //return source.getMaxAngularSpeed();
            }
        }
    }
}
