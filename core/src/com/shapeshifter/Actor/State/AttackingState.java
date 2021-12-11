package com.shapeshifter.Actor.State;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.MovementStrategy.DriftMovementStrategy;
import com.shapeshifter.Actor.MovementStrategy.FollowMovementStrategy;
import com.shapeshifter.Actor.MovementStrategy.MovementStrategy;

public class AttackingState extends State{

    /*
    private final Actor source;
    private final State state;

    public AttackMovementStrategy (Actor source) {
        this.source = source;
        this.state = source.getState();
    }

    @Override
    public float getNewSpeed() {
        if (state.getClass() == HoldState.class) {return 0;}
        if (state.getClass() == FollowingState.class) {return source.getMaxSpeed() * 2;}
        if (state.getClass() == SlowState.class) {return source.getMaxSpeed() / 2;}
        return 0; //Should never do this. Throw error or something
    }

    @Override
    public float getNewAngularSpeed() {
        if (state.getClass() == HoldState.class) {return 0;} //return same as follow
        if (state.getClass() == FollowingState.class) {return 0;}
        if (state.getClass() == SlowState.class) {return source.getMaxAngularSpeed() / 2;}
        return 0; //Should never do this. Throw error or something
    }
     */


    //when constructed, set speed to zero so that the passed in follow movestrat only rotates
    //set texture to hold
    //each tick will keep track of when to progress to next stage and
    //after some duration, set speed to 2*maxspeed and set movestrat to drift
    //after some duration, set speed and angular speed very low and set movestrat to searching??


    public AttackingState(Actor source, MovementStrategy movestrat) {
        this.movestrat = movestrat;
        this.source = source;
        source.setMaxSpeed(0);
        source.setMaxAngularSpeed(10);
        source.setTexture(new Texture("triangle_hold.png"));

    }

    private void charge() {
        source.setTexture(new Texture("triangle_charge.png"));
        source.setMaxSpeed(40);
        source.setMaxAngularSpeed(0);
        this.movestrat = new DriftMovementStrategy(source);
    }

    private void slow() {
        source.setTexture(new Texture("triangle_slow.png"));
        source.setMaxSpeed(20);
        source.setMaxAngularSpeed(5);
        this.movestrat = new DriftMovementStrategy(source);
        //I want to let them go back to searching state while still having the slow debuff. But only the tick here can check for that
        //Idea: make tick abstract only, and just have if statement for whether it is attacking/slow or something, with attributes as needed
    }

    @Override
    public void tick() {
        super.tick();
        if (duration > 90 && movestrat.getClass() != DriftMovementStrategy.class) charge();
        if (duration > 150 && movestrat.getClass() != FollowMovementStrategy.class) slow();
    }
}
