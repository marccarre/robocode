package marcobot;

import marcobot.enemies.EnemyEvent;
import robocode.AdvancedRobot;
import robocode.Condition;
import robocode.CustomEvent;
import robocode.ScannedRobotEvent;

import java.util.Random;

public class MarcoBot extends AdvancedRobot {

    private static final Random RANDOM = new Random();
    private static final String ON_WALL_TOO_CLOSE = "onWallTooClose";
    private static final double WALL_MARGIN = 50;

    private int moveDirection = 1;

    public void run() {
        // Initialization:
        setAdjustRadarForRobotTurn(true); // Independent radar movement.
        addWallTooClose(WALL_MARGIN);

        // Infinite loop:
        while (true) {
            turnRadarLeft(360);
            randomMove();
        }
    }

    private void addWallTooClose(final double margin) {
        addCustomEvent(new Condition(ON_WALL_TOO_CLOSE) {
            public boolean test() {
                return reachedLeftWall(margin) || reachedRightWall(margin) || reachedBottomWall(margin) || reachedTopWall(margin);
            }
        });
    }

    private boolean reachedTopWall(final double wallMargin) {
        return getY() >= getBattleFieldHeight() - wallMargin;
    }

    private boolean reachedBottomWall(final double wallMargin) {
        return getY() <= wallMargin;
    }

    private boolean reachedRightWall(final double wallMargin) {
        return getX() >= getBattleFieldWidth() - wallMargin;
    }

    private boolean reachedLeftWall(final double wallMargin) {
        return getX() <= wallMargin;
    }

    private void randomMove() {
        turn(Direction.randomize(RANDOM));
        moveDirection *= RANDOM.nextBoolean() ? 1 : -1;
        setAhead(moveDirection * (500 + RANDOM.nextInt()));
    }

    private void turn(final Direction direction) {
        switch (direction) {
            case LEFT:
                turnLeft(RANDOM.nextInt(180));
                break;
            case RIGHT:
                turnRight(RANDOM.nextInt(180));
                break;
        }
    }

    public void onCustomEvent(CustomEvent e) {
        if (ON_WALL_TOO_CLOSE.equals(e.getCondition().getName())) {
            // switch directions and move away
            moveDirection *= -1;
            setAhead(moveDirection * (500 + RANDOM.nextInt()));
        }
    }

    public void onScannedRobot(final ScannedRobotEvent e) {
        setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
        fire(1);
    }
}
