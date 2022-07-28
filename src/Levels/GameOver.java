package Levels;

import Default.Counter;
import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class GameOver implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;
    private final Counter score;

    public GameOver(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
}
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.pink);
        d.drawText(50, d.getHeight() / 2, "Game Over! your score is: " + this.score.getValue(), 40);
     
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}