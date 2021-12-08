package com.shapeshifter;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

public class GameWorld {
    public static GameWorld INSTANCE;
    public Texture background= new Texture("background.jpg");

    public ArrayList<Actor> actors = new ArrayList<Actor>();
    public Actor player;


    public GameWorld() {
        INSTANCE = this;
        this.make();
    }

    public void make() {
        for (int i = 0; i < 5000; i++) {
            actors.add(new Actor());
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
