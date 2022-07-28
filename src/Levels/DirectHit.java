package Levels;
import Collidables.Sprite;
import Collidables.Velocity;
import Interfaces.LevelInformation;
import Shapes.Point;
import Shapes.Rectangle;
import ShownObject.Block;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectHit implements LevelInformation {
    private final Sprite backGround;
    private final List<Velocity> ballVelocities;
    public DirectHit() {
        this.backGround = new Block(new Rectangle(new Point(0, 0), 800, 600), Color.black);
        this.ballVelocities = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        this.ballVelocities.add(Velocity.fromAngleAndSpeed(0, 2));
        return this.ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return backGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(385, 100), 30, 30), Color.red);
        list.add(block);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

}
