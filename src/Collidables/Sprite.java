package Collidables;
import biuoop.DrawSurface;
import Shapes.*;
import ShownObject.*;
public interface Sprite {
    void drawOn(DrawSurface d);
    void timePassed();
}