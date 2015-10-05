package marcobot;

import marcobot.enemies.EnemyBot;
import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.RobotDeathEvent;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

public class MarcoBot extends AdvancedRobot {

    private EnemyBot trackedEnemy = new EnemyBot();

    public void onScannedRobot(final ScannedRobotEvent e) {
        if (trackedEnemy.shouldTrack(e)) {
            trackedEnemy.track(e);
            setTurnRight(e.getBearing()); // Turn towards tracked robot.
        }
    }

    public void onRobotHit(final HitRobotEvent e) {
        if (e.isMyFault()) {

        }
    }

    public void onRobotDeath(final RobotDeathEvent e) {
        if (trackedEnemy.is(e.getName())) {
            trackedEnemy.reset();
        }
    }

    public void onWin(final WinEvent e) {
        dance();
    }

    public void run() {
        setAdjustRadarForGunTurn(true); // Independent radar movement.

        trackedEnemy.reset();
        while (true) {
            rotateRadar();
            move();
            fire();
            execute(); // Execute queued-up actions.
        }
    }

    private void rotateRadar() {
        setTurnRadarRight(360);
    }

    private void move() {
        // Move a little closer...
        if (trackedEnemy.distance() > 200)
            setAhead(trackedEnemy.distance() / 2);
        // ...but not too close.
        if (trackedEnemy.distance() < 100)
            setBack(trackedEnemy.distance());
    }

    private void fire() {
        if (trackedEnemy.none() || getGunHeat() > 0)
            return;

        // Only shoot if we're (close to) pointing at our enemy
        if (Math.abs(getTurnRemaining()) < 10) {
            double max = Math.max(getBattleFieldHeight(), getBattleFieldWidth());
            if (trackedEnemy.distance() < max / 3) {
                setFire(Rules.MAX_BULLET_POWER);
            } else {
                setFire(Rules.MIN_BULLET_POWER);
            }
        }
    }

    private void dance() {
        turnRight(360);
        for (int i = 0; i < 3; ++i) {
            ahead(10);
            back(10);
        }
    }
}
