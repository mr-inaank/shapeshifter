package com.shapeshifter;

import com.shapeshifter.Actor.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartitionedWorld {

    private HashMap<String, List<Actor>> partitions;


    public PartitionedWorld() {
        partitions = new HashMap<>();
    }

    public void addActor(Actor actor) {
        List<Actor> correctPartition = getWorldPartition(actor.getPosX(), actor.getPosY());
        correctPartition.add(actor);
    }

    /**
     * Returns a list of hashes that correspond to the 9 nearest neighbours.
     * Index 0 corresponds to the source partition.
     * Index 1 corresponds to the right neighbour.
     * Remaining indicies are incremented, by moving anticlockwise.
     * 4  3  2
     * 5  0  1
     * 6  7  8
     */
    public List<List<Actor>> get9NeighbourPartitions(float x, float y, float radius) {
        List<List<Actor>> neighbours = new ArrayList<>();

//        int[] xChanges = {0, 1, 1, 0, -1, -1, -1, 0, 1};
//        int[] yChanges = {0, 0, -1, -1, -1, 0, 1, 1, 1};
        int maxRadius = (int) Math.ceil(radius/1000);
        for (int i = -maxRadius; i <= maxRadius; i++) {
            for (int j = -maxRadius; j <= maxRadius; j++) {
                neighbours.add(getWorldPartition(x + i, y + j));
            }
        }

        return neighbours;
    }

    private String calcPointHash(float x, float y) {
        return String.format("%d_%d", Math.round(x/1000), Math.round(y/1000));
    }

    private List<Actor> getWorldPartition(float x, float y) {
        String hash = calcPointHash(x, y);

        List<Actor> partition = partitions.get(hash);
        if (partition == null) {
            partition = new ArrayList<>();
            partitions.put(hash, partition);
        }

        return partition;
    }
}
