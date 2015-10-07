package Westminster.Team5;

import Westminster.Team5.marccarre.State;
import Westminster.Team5.marccarre.capabilities.Dodger;
import Westminster.Team5.marccarre.capabilities.EnemiesTracker;
import Westminster.Team5.marccarre.capabilities.radar.ContinuousRadar;
import Westminster.Team5.marccarre.capabilities.radar.Radar;
import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.RobotDeathEvent;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

import java.awt.Color;

public class MarcCarre extends AdvancedRobot {

    private static final int MOVE_CLOSER_TO_ENEMY = 30; // 0 = neutral, + = move closer, - = move away.

    private final EnemiesTracker enemiesTracker = new EnemiesTracker();
    private final Dodger dodger = new Dodger(this, enemiesTracker);
    private final Radar radar = new ContinuousRadar(this);

    private State targettedEnemy;

    public void onScannedRobot(final ScannedRobotEvent e) {
        final State state = enemiesTracker.log(e);
        if ((targettedEnemy == null) || (targettedEnemy.name().equals(e.getName())) || (state.equals(enemiesTracker.closest()))) {
            targettedEnemy = state;

            setTurnRight(targettedEnemy.bearing() + 90 - MOVE_CLOSER_TO_ENEMY);
        }

        dodger.dodgeIfHasFired();
    }

    public void onHitWall(final HitWallEvent e) {
        back(100);
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
        setColors(Color.BLACK, Color.BLACK, Color.BLACK, Color.YELLOW, Color.CYAN);

        // Independent radar movement:
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);

        turnGunLeft(90 - MOVE_CLOSER_TO_ENEMY); // Set gun at 90° to better dodge.

        while (true) {
            radar.scan();
            move();
            fire();
            execute(); // Execute queued-up actions.
        }
    }

    private void move() {
        if (targettedEnemy == null) {
            return;
        }

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
        if (!canFire()) {
            return;
        }
        if (isAlmostFacingEnemy()) {
            setFire(Math.min(400 / targettedEnemy.distance(), Rules.MAX_BULLET_POWER));
        }
    }

    private boolean canFire() {
        return (targettedEnemy != null) && (getGunHeat() == 0);
    }

    private boolean isAlmostFacingEnemy() {
        return Math.abs(getTurnRemaining()) < 10;
    }

    private void dance() {
        final int angle = 30;
        for (int i = 0; i < 5; ++i) {
            setTurnLeft(angle);
            setTurnGunRight(angle);
            setTurnRadarLeft(angle);

            setTurnRight(angle);
            setTurnGunLeft(angle);
            setTurnRadarRight(angle);
        }
        execute();
    }

    private void turnRadarTowardsTarget(final ScannedRobotEvent e) {
        setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
    }

    private void turnGunTowardsTarget(final ScannedRobotEvent e) {
        setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
    }
}
