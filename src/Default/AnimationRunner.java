package Default;
import Interfaces.Animation;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    public AnimationRunner(GUI gui) {
      this.framesPerSecond = 60;
      this.gui = gui;
      this.sleeper = new Sleeper();
    }

    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
