package com.shapeshifter.Actor.AttackStrategy;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;

public class SlowAttackStrategy implements AttackStrategy{

    private final Actor source;

    public SlowAttackStrategy(Actor source) {
        this.source = source;
        source.setTexture(new Texture("triangle.png"));
    }

    @Override
    public void nextStage() {
        source.setAttack(new HoldAttackStrategy(source));
    }

    @Override
    public void tick() {

    }
}
