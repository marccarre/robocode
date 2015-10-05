package marcobot.enemies;

import robocode.ScannedRobotEvent;

public class EnemyBot {

    private static final String EMPTY = "";
    private double bearing;
    private double distance;
    private double energy;
    private double heading;
    private String name;
    private double velocity;

    public EnemyBot() {
        reset();
    }

    public void reset() {
        bearing = 0.0;
        distance = 0.0;
        energy = 0.0;
        heading = 0.0;
        name = EMPTY;
        velocity = 0.0;
    }

    final public void track(ScannedRobotEvent e) {
        bearing = e.getBearing();
        distance = e.getDistance();
        energy = e.getEnergy();
        heading = e.getHeading();
        name = e.getName();
        velocity = e.getVelocity();
    }

    public boolean none() { return name.equals(EMPTY); }

    public double bearing()  { return bearing; }
    public double distance() { return distance; }
    public double energy()   { return energy; }
    public double heading()  { return heading; }
    public String name()     { return name; }
    public double velocity() { return velocity; }

    public boolean is(final String name) {
        return name().equals(name);
    }

    public boolean isFurtherAwayThan(final double distance) {
        return distance < distance() - 70;
    }

    public boolean shouldTrack(final ScannedRobotEvent e) {
        return none() || is(e.getName()) || isFurtherAwayThan(e.getDistance());
    }

    public boolean shouldTrack(ScannedRobotEvent e, long closer) {
        return shouldTrack(e);
    }
}
