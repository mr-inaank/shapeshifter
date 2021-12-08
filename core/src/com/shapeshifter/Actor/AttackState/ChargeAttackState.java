package com.shapeshifter.Actor.AttackState;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;

public class ChargeAttackState implements AttackState {

    private final Actor source;
    private int duration;

    public ChargeAttackState(Actor source) {
        this.duration = 0;
        this.source = source;
        source.setTexture(new Texture("triangle_charge.png"));
    }

    @Override
    public void tick() {
        duration++;
    }

    @Override
    public void nextStage() {
        source.setAttack(new SlowAttackState(source));
    }


}
