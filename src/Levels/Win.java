package Levels;

import Default.Counter;
import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;
public class Win implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;
    private final Counter score;

    public Win(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.PINK);
        d.drawText(50, d.getHeight() / 2, "You Win! Your score is: " + this.score.getValue(), 40);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
