package Westminster.Team5;

import Westminster.Team5.marccarre.capabilities.Dodger;
import Westminster.Team5.marccarre.capabilities.EnemiesTracker;
import Westminster.Team5.marccarre.State;
import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.RobotDeathEvent;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

public class MarcCarre extends AdvancedRobot {

    private static final int MOVE_CLOSER_TO_ENEMY = 30; // 0 = neutral, + = move closer, - = move away.

    private final EnemiesTracker enemiesTracker = new EnemiesTracker();
    private final Dodger dodger = new Dodger(this, enemiesTracker);

    private State targettedEnemy;

    public void onScannedRobot(final ScannedRobotEvent e) {
        final State state = enemiesTracker.log(e);
        if ((targettedEnemy == null) || (targettedEnemy.name().equals(e.getName())) || (state.equals(enemiesTracker.closest()))) {
            targettedEnemy = state;

            setTurnRight(targettedEnemy.bearing() + 90 - MOVE_CLOSER_TO_ENEMY);
        }

        dodger.dodgeIfHasFired();
    }

    public void onRobotHit(final HitRobotEvent e) {
        if (e.isMyFault()) {
        }
    }

    public void onHitByBullet(final HitByBulletEvent e) {
        e.getName();
        e.getBullet();
    }

    public void onRobotDeath(final RobotDeathEvent e) {
        if ((targettedEnemy != null) && (targettedEnemy.name().equals(e.getName()))) {
            targettedEnemy = null;
        }
    }

    public void onWin(final WinEvent e) {
        dance();
    }

    public void run() {
        setAdjustRadarForGunTurn(true); // Independent radar movement.
        turnGunLeft(90 - MOVE_CLOSER_TO_ENEMY); // Set gun at 90° to better dodge.

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
        if (targettedEnemy == null)
            return;

        // Move a little closer...
        if (targettedEnemy.distance() > 200) {
            setAhead(targettedEnemy.distance() / 2);
        }
        // ...but not too close.
        if (targettedEnemy.distance() < 100) {
            setBack(targettedEnemy.distance());
        }
    }

    private void fire() {
        if ((targettedEnemy == null) || (getGunHeat() > 0))
            return;

        // Only shoot if we're (close to) pointing at our enemy
        if (Math.abs(getTurnRemaining()) < 10) {
            double max = Math.max(getBattleFieldHeight(), getBattleFieldWidth());
            if (targettedEnemy.distance() < max / 3) {
                setFire(Rules.MAX_BULLET_POWER);
            } else {
                setFire(Rules.MIN_BULLET_POWER);
            }
        }
    }

    private void dance() {
        for (int i = 0; i < 5; ++i) {
            turnRight(45);
            turnLeft(45);
        }
    }
}
