package com.shapeshifter;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.Square;
import com.shapeshifter.Actor.Triangle;

import java.util.ArrayList;

public class GameWorld {
    public static GameWorld INSTANCE;
    public Texture background= new Texture("background.jpg");


    public ArrayList<Actor> actors = new ArrayList<Actor>();
    public Actor player;


    public GameWorld() {
        INSTANCE = this;
        this.testSpawns();

    }


    public void testSpawns() {
        actors.add(new Square());
        for (int i = 0; i < 500; i++) {
            actors.add(new Triangle());
        }
        player = actors.get(0);
    }

    public void gameLoop() {
        for (Actor i : actors) {
            i.aim(player);
            i.move();
            i.collide();
        }
        player.userControl();
    }

}
