package com.shapeshifter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.Circle;
import com.shapeshifter.Actor.State.UserState;
import com.shapeshifter.Actor.State.SearchingState;
import com.shapeshifter.Actor.Triangle;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        player.setFaction(0);
        actors.add(player);

        for (int i = 0; i < 100; i++) {
            Actor newActor = new Triangle();
            newActor.setState(new SearchingState(newActor));
            newActor.setFaction(1);
            actors.add(newActor);
        }

    }

    public void gameLoop() {
        for (Actor i : actors) {
            i.updateState();
        }
        for (Actor i : actors) {
            i.aim();
            i.move();
            i.collide();
        }
    }

    public List<Actor> getNearbyActors(Actor source) {
        List<Actor> nearby = new ArrayList<Actor>();
        for (Actor i : actors) {
            double distance = Math.pow((i.getPosX() - source.getPosX()), 2) + Math.pow((i.getPosY() - source.getPosY()), 2);
            if (distance < 600000) {
                if (i != source) nearby.add(i);
            }
        }
        return nearby;
    }

}
