package Default;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop = false;
    private boolean isAlreadyPressed = true;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if ((this.sensor.isPressed(this.key)) && (!this.isAlreadyPressed)) {
            this.stop = true;
        }
        if (!this.sensor.isPressed(this.key)) {
          this.isAlreadyPressed = false;
        }
        if (this.sensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
