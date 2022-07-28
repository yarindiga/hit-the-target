package Collidables;
import Interfaces.Animation;
import Interfaces.LevelInformation;
import Levels.GameOver;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;
import Shapes.Rectangle;
import Shapes.Point;
import ShownObject.Paddle;
import ShownObject.Block;
import ShownObject.Ball;
import ShownObject.ScoreIndicator;
import Default.*;

public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter counter;
    private final Counter counterBall;
    private final Counter counterScore;
    private ScoreIndicator scoreIndicator;
    private GUI gui;
    private Block b;
    private final AnimationRunner runner;
    private boolean running;
    private final biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private boolean win;
    private boolean lose;
    private int numOfBlock;

    /**
     * Game constructor.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.gui = new GUI("BAR", 800, 600);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        counter = new Counter(levelInformation.numberOfBlocksToRemove());
        counterBall = new Counter(levelInformation.numberOfBalls());
        counterScore = new Counter(0);
        this.runner = new AnimationRunner(gui);
        keyboard = gui.getKeyboardSensor();
        this.levelInformation = levelInformation;
        this.win = false;
        this.lose = false;
        numOfBlock = levelInformation.numberOfBalls();
    }

    public GameLevel(LevelInformation levelInformation, GUI gui, Counter score) {
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        counter = new Counter(levelInformation.numberOfBlocksToRemove());
        counterBall = new Counter(levelInformation.numberOfBalls());
        counterScore = score;
        this.runner = new AnimationRunner(gui);
        keyboard = gui.getKeyboardSensor();
        this.levelInformation = levelInformation;
        this.win = false;
        this.lose = false;
    }

    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner) {
        this.gui = new GUI(levelInformation.levelName(), 800, 600);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        counter = new Counter(0);
        counterBall = new Counter(levelInformation.numberOfBalls());
        counterScore = new Counter(0);
        this.runner = animationRunner;
        keyboard = keyboardSensor;
        this.levelInformation = levelInformation;
    }

    public GameLevel() {
        this.gui = new GUI("BAR", 800, 600);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        counter = new Counter(0);
        counterBall = new Counter(30);
        counterScore = new Counter(0);
        this.runner = new AnimationRunner(gui);
        keyboard = gui.getKeyboardSensor();
    }

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.notifyAllTimePassed(s);
    }

    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, this.counter);
        BallRemover ballRemover = new BallRemover(this, this.counterBall);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.counterScore);
        scoreIndicator = new ScoreIndicator(this.counterScore);
        PrintingHitListener print = new PrintingHitListener();
        //creat block.
        Point point = new Point(0, 0);
        Rectangle r = new Rectangle(point, 800, 600);
        this.b = new Block(r);
        this.b.setColor(Color.pink);
        this.sprites.notifyAllTimePassed(this.levelInformation.getBackground());
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Point p = new Point(400, 520);
            Ball ball = new Ball(p, 6, Color.WHITE);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            ball.setGameEnvironment(this.environment);
        }
        point = new Point((800 - levelInformation.paddleWidth()) / 2, 560);
        r = new Rectangle(point, levelInformation.paddleWidth(), 20);
        biuoop.KeyboardSensor key = this.gui.getKeyboardSensor();
        Paddle paddle = new Paddle(key, r);
        paddle.setColor(Color.YELLOW);
        paddle.addToGame(this);
        for (int i = 0; i < levelInformation.numberOfBlocksToRemove(); i++) {
            Block block = levelInformation.blocks().get(i);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
        }
        Block score = new Block(new Rectangle(new Point(0, 0), 800, 20));
        Block up = new Block(new Rectangle(new Point(0, 20), 800, 20));
        Block down = new Block(new Rectangle(new Point(0, 600), 780, 0));
        Block left = new Block(new Rectangle(new Point(0, 20), 20, 600));
        Block right = new Block(new Rectangle(new Point(780, 20), 20, 580));
        left.setColor(Color.lightGray);
        down.setColor(Color.lightGray);
        up.setColor(Color.lightGray);
        right.setColor(Color.lightGray);
        score.setColor(Color.white);
        score.addToGame(this);
        scoreIndicator.addToGame(this);
        up.addToGame(this);
        down.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
        down.addHitListener(ballRemover);

    }


    public void removeCollidable(Collidable c) {
        environment.removeCollid(c);
    }

    public void removeSprite(Sprite s) {
        sprites.removeSpirte(s);
    }

    public void run() {
        runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.sprites.drawAllOn(d);
        this.sprites.timePassed();
        d.setColor(Color.black);
        d.drawText(40, 16, "Level Name: " + this.levelInformation.levelName(), 16);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }

        if (this.counterBall.getValue() == 0) {
            this.lose = true;
        }
        if (this.counter.getValue() == 0) {
            this.numOfBlock = 0;
            this.win = true;
        }
        if (this.counterBall.getValue() == 0) {
            while (!this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                        KeyboardSensor.SPACE_KEY, new GameOver(this.keyboard, counterScore)));
            }
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.running = false;
                gui.close();
            }
        }

    }

    @Override
    public boolean shouldStop() {
         if (this.counter.getValue() == 0) {
                this.numOfBlock = 0;
                this.win = true;
                this.running = false;
            }

            return !this.running;
        }

    public boolean getWin() {
        return this.win;
    }

    public boolean getLose() {
        return this.lose;
    }

    public int getNumOfBlock() {
        return this.numOfBlock;
    }
}