package Westminster.Team5.marccarre;

public class Calculations {
    private Calculations() {
        // Pure utility class, do NOT instantiate.
    }

    /**
     * Calculate bullet's velocity.
     * See also: http://robowiki.net/wiki/Robocode/FAQ#Game_physics
     * @param power power of the bullet for which we want to calculate velocity.
     * @return bullet's velocity.
     */
    public static double bulletVelocity(final double power) {
        return 20 - 3 * power;
    }
}
