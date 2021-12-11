package com.shapeshifter.Actor.State;

import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.MovementStrategy.MovementStrategy;

abstract public class State {

    protected int duration;
    protected Actor source;
    protected MovementStrategy movestrat;

    protected State() {
        duration = 0;
    }

    public void tick() {
        duration++;
    }
    public MovementStrategy getMovestrat() {
        return this.movestrat;
    }

}
