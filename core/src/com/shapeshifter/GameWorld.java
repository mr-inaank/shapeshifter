package com.shapeshifter;

import java.util.ArrayList;

public class GameWorld {
    public ArrayList<Actor> actors = new ArrayList<Actor>();

    public GameWorld() {
        this.make();
    }

    public void make() {
        for (int i = 0; i < 50; i++) {
            actors.add(new Actor());
        }
    }

    public void gameLoop() {
        for (Actor i : actors) {
            i.move();
            i.aim();
        }
    }

}
