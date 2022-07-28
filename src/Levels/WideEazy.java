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

public class WideEazy implements LevelInformation {
    private final Sprite backGround;
    private final List<Velocity> ballVelocities;

    public WideEazy() {
        this.backGround = new Block(new Rectangle(new Point(0, 0), 800, 600), Color.WHITE);
        this.ballVelocities = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int x = 70;
        for (int i = 0; i < 15; i++) {
            this.ballVelocities.add(Velocity.fromAngleAndSpeed(x, 2));
            x = x - 15;

        }
        return this.ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Eazy";
    }

    @Override
    public Sprite getBackground() {
        return this.backGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (i < 2) {
                Block block = new Block(new Rectangle(new Point(20 + i * 50.66, 300), 50.66, 20), Color.red);
                list.add(block);
            } else if (i < 4) {
                Block block = new Block(new Rectangle(new Point(20 + i * 50.66, 300), 50.66, 20), Color.orange);
                list.add(block);
            } else if (i < 6) {
                Block block = new Block(new Rectangle(new Point(20 + i * 50.66, 300), 50.66, 20), Color.yellow);
                list.add(block);
            } else if (i < 9) {
                Block block = new Block(new Rectangle(new Point(20 + i * 50.66, 300), 50.66, 20), Color.green);
                list.add(block);
            } else if (i < 11) {
                Block block = new Block(new Rectangle(new Point(20 + i * 50.66, 300), 50.66, 20), Color.blue);
                list.add(block);
            } else if (i < 13) {
                Block block = new Block(new Rectangle(new Point(20 + i * 50.66, 300), 50.66, 20), Color.pink);
                list.add(block);
            } else {
                Block block = new Block(new Rectangle(new Point(20 + i * 50.66, 300), 50.66, 20), Color.cyan);
                list.add(block);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
