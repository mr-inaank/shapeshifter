package com.shapeshifter;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.AttackState.SlowAttackState;
import com.shapeshifter.Actor.Circle;
import com.shapeshifter.Actor.MovementStrategy.FollowMovementStrategy;
import com.shapeshifter.Actor.MovementStrategy.UserMovementStrategy;
import com.shapeshifter.Actor.Triangle;

import java.util.ArrayList;

public enum GameWorld {
    INSTANCE;
    public Texture background= new Texture("background.jpg");


    public ArrayList<Actor> actors = new ArrayList<Actor>();
    public Actor player;


    private GameWorld() {
        this.testSpawns();
    }


    public void testSpawns() {
        player = new Circle();
        player.setStrategy(new UserMovementStrategy(player));
        actors.add(player);

        for (int i = 0; i < 500; i++) {
            Actor newActor = new Triangle();
            newActor.setStrategy(new FollowMovementStrategy(newActor, player));
            newActor.setAttack(new SlowAttackState(newActor));
            actors.add(newActor);
        }

    }

    public void gameLoop() {
        for (Actor i : actors) {
            i.useSkill();
            i.aim();
            i.move();
            i.collide();
        }
    }

}
