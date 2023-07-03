package model;

import object.Collisional;
import object.Maze;

import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {
    boolean containsMaze = false;
    private final CopyOnWriteArrayList<Collisional> objectsInCell;
    public Cell() {
        objectsInCell = new CopyOnWriteArrayList<>();
    }
    public void addObject(Collisional object) {
        if (containsMaze) {
            return;
        }
        if (object instanceof Maze) {
            containsMaze = true;
        }
        objectsInCell.add(object);
    }
    public void removeObject(Collisional object) {
        objectsInCell.remove(object);
    }
    public CopyOnWriteArrayList<Collisional> getObjectsInCell() {
        return objectsInCell;
    }
    public Collisional getContent() {
        if (objectsInCell.isEmpty()) {
            return null;
        }
        return objectsInCell.get(objectsInCell.size() - 1);
    }
}