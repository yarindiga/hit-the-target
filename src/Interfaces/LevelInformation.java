package Interfaces;

import Collidables.Sprite;
import Collidables.Velocity;
import ShownObject.Block;
import java.util.List;

public interface LevelInformation {
    int numberOfBalls();
    List<Velocity> initialBallVelocities();
    int paddleSpeed();
    int paddleWidth();
    String levelName();
    Sprite getBackground();
    List<Block> blocks();
    int numberOfBlocksToRemove();
}
