package com.shapeshifter.Actor.AttackState;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;

public class HoldAttackState implements AttackState {

    private final Actor source;
    private int duration;

    public HoldAttackState(Actor source) {
        this.duration = 0;
        this.source = source;
        source.setTexture(new Texture("triangle_hold.png"));
    }

    @Override
    public void tick() {
        duration++;
    }

    @Override
    public void nextStage() {
        if (duration > 180) {
            source.setAttack(new ChargeAttackState(source));
        }
    }




}
