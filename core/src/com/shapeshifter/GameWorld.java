package com.shapeshifter;

import com.badlogic.gdx.graphics.Texture;
import com.shapeshifter.Actor.Actor;
import com.shapeshifter.Actor.Circle;
import com.shapeshifter.Actor.State.SearchingState;
import com.shapeshifter.Actor.State.UserState;
import com.shapeshifter.Actor.Triangle;

import java.util.ArrayList;
import java.util.List;

public enum GameWorld {
    INSTANCE;
    public Texture background = new Texture("background.jpg");


    public ArrayList<Actor> actors = new ArrayList<Actor>();
    public Actor player;

    private PartitionedWorld partitionedWorld;


    private GameWorld() {
        this.testSpawns();
    }

    public void testSpawns() {
        player = new Circle();
        player.setState(new UserState(player));
        actors.add(player);

        for (int i = 0; i < 10000; i++) {
            Actor newActor = new Triangle();
            newActor.setState(new SearchingState(newActor));
            actors.add(newActor);
        }
    }

    public void gameLoop() {
        updatePartitionedWorld();
        getNearbyActors(player, 1000).size();

        for (Actor i : actors) {
            i.updateState();
        }

        for (Actor i : actors) {
            i.aim();
            i.move();
            i.collide();
        }
    }

    public List<Actor> getNearbyActors(Actor source, float radius) {
        List<Actor> result = new ArrayList<Actor>();

        List<List<Actor>> partitions = partitionedWorld.get9NeighbourPartitions(source.getPosX(), source.getPosY(), radius);
        for (List<Actor> partition : partitions) {
            for (Actor actor : partition) {
                if (actor == source) {
                    continue;
                }
//                System.err.println(calcSquaredDistance(source, actor));
                if (calcSquaredDistance(source, actor) < radius * radius) {
                    result.add(actor);
                }
            }
        }

        return result;
    }

    private void updatePartitionedWorld() {
        partitionedWorld = new PartitionedWorld();
        for (Actor actor : actors) {
            partitionedWorld.addActor(actor);
        }
    }

    private float calcSquaredDistance(Actor a, Actor b) {
        float deltaX = a.getPosX() - b.getPosX();
        float deltaY = a.getPosY() - b.getPosY();

        return deltaX * deltaX + deltaY * deltaY;
    }

}
