package com.shapeshifter.Actor.State;

import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.MovementStrategy.DriftMovementStrategy;
import com.shapeshifter.Actor.MovementStrategy.FollowMovementStrategy;
import com.shapeshifter.GameWorld;


public class FollowingState extends State {

    private Actor target;

    public FollowingState(Actor source, Actor target) {
        this.source = source;
        this.target = target;
        this.movestrat = new FollowMovementStrategy(source, target);
    }


    @Override
    public void tick() {
        super.tick();

        //check distance to target
        double distance = Math.pow((target.getPosX() - source.getPosX()), 2) + Math.pow((target.getPosY() - source.getPosY()), 2);

        //if lost sight, go back to searching
        if (distance > 800000) {
            source.setState(new SearchingState(source));
        }
        //if very close, go into attack
        if (distance < 50000) {
            source.setState(new AttackingState(source, movestrat));
        }
    }
}
