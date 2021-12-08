package com.shapeshifter.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.MovementStrategy.FollowMovementStrategy;
import com.shapeshifter.Actor.MovementStrategy.MovementStrategy;

public class Square extends Actor{

    public Square() {
        super();
        this.texture = new Texture("square.png");
    }
}
