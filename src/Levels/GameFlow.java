package Levels;

import Collidables.GameLevel;
import Default.AnimationRunner;
import Default.Counter;
import Default.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import Interfaces.LevelInformation;
import Levels.Win;

import java.util.List;

public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter score;
    private final GUI gui;

    public GameFlow() {
        this.gui = new GUI("BAR", 800, 600);
        this.keyboardSensor = this.gui.getKeyboardSensor();
        this.animationRunner = new AnimationRunner(gui);
        this.score = new Counter(0);
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            Green3 green3 = new Green3();
            GameLevel level = new GameLevel(levelInfo, gui, score);
            level.initialize();
            while ((!level.getWin()) && (!level.getLose())) {
                level.run();
            }
            if (level.getWin()) {
                score.increase(100);
            }
        }
        while (!this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new Win(this.keyboardSensor, score)));
        }
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            gui.close();
        }
    }
}
