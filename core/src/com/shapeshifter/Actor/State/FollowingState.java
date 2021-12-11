package com.shapeshifter.Actor.State;

import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.MovementStrategy.DriftMovementStrategy;
import com.shapeshifter.Actor.MovementStrategy.FollowMovementStrategy;
import com.shapeshifter.GameWorld;

import java.util.Random;

public class FollowingState extends State {

    private Random rand = new Random();

    public FollowingState(Actor source, Actor target) {
        this.source = source;
        this.movestrat = new FollowMovementStrategy(source, target);
    }


    @Override
    public void tick() {
        super.tick();
        //check that target is still in vision
        //if not, switch state to searching

        //check if there is an enemy in attack range
        //if there is, switch to attack state, passing in the current movestrat
        if (rand.nextInt(1000) == 1) {
            source.setState(new AttackingState(source, movestrat));
        }

        }
    }
