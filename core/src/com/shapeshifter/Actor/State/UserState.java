package com.shapeshifter.Actor.State;

import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.MovementStrategy.UserMovementStrategy;

public class UserState extends State{

    public UserState(Actor source) {
        this.source = source;
        source.setMaxSpeed(50);
        System.out.println(source.getMaxSpeed());
        source.setMaxAngularSpeed(5);
        this.movestrat = new UserMovementStrategy(source);
    }
    /*
    @Override
    public void tick() {
        super.tick();
    }
    */

}
