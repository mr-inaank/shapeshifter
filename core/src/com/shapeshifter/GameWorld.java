package com.shapeshifter;

import com.badlogic.gdx.graphics.Texture;

import javax.swing.*;
import java.util.ArrayList;

public class GameWorld {
    public ArrayList<Actor> actors = new ArrayList<Actor>();
    public Texture background= new Texture("background.jpg");

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
