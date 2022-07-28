package Default;

import Collidables.SpriteCollection;
import Interfaces.Animation;
import biuoop.DrawSurface;

import java.awt.*;
public class CountdownAnimation implements Animation {
    private Double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long begin;
    private long rTime;
    private long beginRTime;
    private boolean stop;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.begin = System.currentTimeMillis();
        this.rTime = (long) (1000 * numOfSeconds) / this.countFrom;
        this.beginRTime = rTime;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.fillCircle(405, 390, 30);
        d.setColor(Color.white);
        d.fillCircle(405, 390, 23);
        d.setColor(Color.red);
        d.drawText(400, 400, "" + this.countFrom, 32);
        long time = System.currentTimeMillis() - begin;
        if (time > rTime) {
            this.countFrom--;
            rTime = rTime + beginRTime;
        }
        if (this.countFrom == 0) {
            this.stop = true;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
