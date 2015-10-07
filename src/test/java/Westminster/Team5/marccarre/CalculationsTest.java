package Westminster.Team5.marccarre.capabilities;

import Westminster.Team5.marccarre.Calculations;
import org.junit.Test;

import static Westminster.Team5.marccarre.Calculations.TOLERANCE_ON_DOUBLES;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import static robocode.Rules.MAX_BULLET_POWER;
import static robocode.Rules.MIN_BULLET_POWER;

public class CalculationsTest {
    @Test
    public void bulletVelocity() {
        assertThat(Calculations.bulletVelocity(MIN_BULLET_POWER), is(19.7));
        assertThat(Calculations.bulletVelocity(MAX_BULLET_POWER), is(11.0));
    }

    @Test
    public void absoluteBearing() {
        assertThat(Calculations.absoluteBearing(100, 100, 100, 200), is(closeTo(0.0, TOLERANCE_ON_DOUBLES)));
        assertThat(Calculations.absoluteBearing(100, 100, 200, 200), is(closeTo(45.0, TOLERANCE_ON_DOUBLES)));
        assertThat(Calculations.absoluteBearing(100, 100, 200, 100), is(closeTo(90.0, TOLERANCE_ON_DOUBLES)));
        assertThat(Calculations.absoluteBearing(100, 100, 200, 0), is(closeTo(135.0, TOLERANCE_ON_DOUBLES)));
        assertThat(Calculations.absoluteBearing(100, 100, 100, 0), is(closeTo(180.0, TOLERANCE_ON_DOUBLES)));
        assertThat(Calculations.absoluteBearing(100, 100, 0, 0), is(closeTo(225.0, TOLERANCE_ON_DOUBLES)));
        assertThat(Calculations.absoluteBearing(100, 100, 0, 100), is(closeTo(270.0, TOLERANCE_ON_DOUBLES)));
        assertThat(Calculations.absoluteBearing(100, 100, 0, 200), is(closeTo(315.0, TOLERANCE_ON_DOUBLES)));
        assertThat(Calculations.absoluteBearing(100, 100, 100, 100), is(closeTo(0.0, TOLERANCE_ON_DOUBLES)));
    }
}
