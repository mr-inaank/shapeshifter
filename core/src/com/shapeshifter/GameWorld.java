package com.shapeshifter;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.Circle;
import com.shapeshifter.Actor.State.UserState;
import com.shapeshifter.Actor.State.SearchingState;
import com.shapeshifter.Actor.Triangle;

import javax.sound.midi.SysexMessage;
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
        player.setState(new UserState(player));
        actors.add(player);

        for (int i = 0; i < 100; i++) {
            Actor newActor = new Triangle();
            newActor.setState(new SearchingState(newActor));
            actors.add(newActor);
        }

    }

    public void gameLoop() {
        for (Actor i : actors) {
            i.updateState();
            i.aim();
            i.move();
            i.collide();
        }
    }

}
