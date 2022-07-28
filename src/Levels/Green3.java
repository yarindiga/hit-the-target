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

public class Green3 implements LevelInformation {
    private final Sprite backGround;
    private final List<Velocity> ballVelocities;
    public Green3() {
        this.backGround = new Block(new Rectangle(new Point(0, 0), 800, 600), Color.green);
        this.ballVelocities = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int x = 50;
        for (int i = 0; i < 2; i++) {
            this.ballVelocities.add(Velocity.fromAngleAndSpeed(x, 2));
            x = -x;

        }
        return this.ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return this.backGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int x = 780;
            int y = 180;
            for (int j = 0; j < 10 - i; j++) {
                Block block = new Block(new Rectangle(new Point(x, y + (i * 25)), 50, 25));
                if (i == 0) {
                    block.setColor(Color.gray);
                } else if (i == 1) {
                    block.setColor(Color.red);
                } else if (i == 2) {
                    block.setColor(Color.yellow);
                } else if (i == 3) {
                    block.setColor(Color.blue);
                } else {
                    block.setColor(Color.WHITE);
                }
                x = x - 50;
                list.add(block);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
