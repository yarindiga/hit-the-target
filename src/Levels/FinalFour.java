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

public class FinalFour implements LevelInformation {
    private final Sprite backGround;
    private final List<Velocity> ballVelocities;
    public FinalFour() {
        Color c = new Color(95, 174, 190);
        this.backGround = new Block(new Rectangle(new Point(0, 0), 800, 600), c);
        this.ballVelocities = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int x = 50;
        for (int i = 0; i < 3; i++) {
            this.ballVelocities.add(Velocity.fromAngleAndSpeed(x, 2));
            x = x - 50;

        }
        return this.ballVelocities;
    }


    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return this.backGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Double x = 780.0;
            int y = 120;
            for (int j = 0; j < 15; j++) {
                Block block = new Block(new Rectangle(new Point(x, y + (i * 25)), 54, 25));
                if (i == 0) {
                    block.setColor(Color.gray);
                } else if (i == 1) {
                    block.setColor(Color.red);
                } else if (i == 2) {
                    block.setColor(Color.yellow);
                } else if (i == 3) {
                    block.setColor(Color.blue);
                } else if (i == 3) {
                    block.setColor(Color.blue);
                } else if (i == 4) {
                    block.setColor(Color.WHITE);
                } else if (i == 5) {
                    block.setColor(Color.cyan);
                } else {
                    block.setColor(Color.pink);
                }
                x = x - 54;
                list.add(block);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
