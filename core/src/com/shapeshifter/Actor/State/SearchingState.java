package com.shapeshifter.Actor.State;

import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.MovementStrategy.DriftMovementStrategy;
import com.shapeshifter.Actor.MovementStrategy.FollowMovementStrategy;
import com.shapeshifter.GameWorld;

import java.util.Random;

public class SearchingState extends State{


    public SearchingState(Actor source) {
        this.source = source;
        this.movestrat = new DriftMovementStrategy(source); //Make this BoidMovementStrategy when ready
    }

    @Override
    public void tick() {
        super.tick();
        //check if there is an enemy in vision
        //if there is, change state to following and pass in the target
        if (true) {
            source.setState(new FollowingState(source, GameWorld.INSTANCE.player));
        }

    }

}
