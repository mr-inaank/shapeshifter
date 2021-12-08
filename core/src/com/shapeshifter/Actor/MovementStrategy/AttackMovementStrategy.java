package com.shapeshifter.Actor.MovementStrategy;

import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.AttackState.AttackState;
import com.shapeshifter.Actor.AttackState.HoldAttackState;

public class AttackMovementStrategy implements MovementStrategy{

    private final Actor source;
    private final AttackState attackState;

    public AttackMovementStrategy (Actor source) {
        this.source = source;
        this.attackState = source.getAttack();
    }

    @Override
    public float getNewSpeed() {
        //if attackstate is hold, return 0
        //if attackstate is charge, return 2*max
        //if attackstate is slow, return max/2
    }

    @Override
    public float getNewAngularSpeed() {
        //if attackstate is hold, same as follow
        //if attackstate is charge, return 0
        //if attackstate is slow, return max/2
    }
}
