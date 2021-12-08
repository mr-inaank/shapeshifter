package com.shapeshifter.Actor.AttackState;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;

public class SlowAttackState implements AttackState {

    private final Actor source;

    public SlowAttackState(Actor source) {
        this.source = source;
        source.setTexture(new Texture("triangle_slow.png"));
    }

    @Override
    public void nextStage() {
        source.setAttack(new HoldAttackState(source));
    }

    @Override
    public void tick() {

    }
}
