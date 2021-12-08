package com.shapeshifter.Actor.AttackStrategy;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;

public class ChargeAttackStrategy implements AttackStrategy{

    private final Actor source;
    private int duration;

    public ChargeAttackStrategy(Actor source) {
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
        source.setAttack(new SlowAttackStrategy(source));
    }


}
