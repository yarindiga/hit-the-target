package Collidables;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
import Shapes.*;
import ShownObject.*;
public class SpriteCollection {
    private List<Sprite> list;
    public SpriteCollection() {
        this.list = new ArrayList<>();
    }

    public void notifyAllTimePassed(Sprite s) {
        this.list.add(s);
    }

    public void timePassed() {
        for (int i = 0; i < list.size(); i++) {
            this.list.get(i).timePassed();
        }
    }

    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < list.size(); i++) {
            this.list.get(i).drawOn(d);
        }
    }
    public void removeSpirte(Sprite s){
        list.remove(s);
    }
}