package Westminster.Team5.marccarre.capabilities;

import Westminster.Team5.marccarre.Calculations;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static robocode.Rules.MAX_BULLET_POWER;
import static robocode.Rules.MIN_BULLET_POWER;

public class CalculationsTest {
    @Test
    public void bulletVelocity() {
        assertThat(Calculations.bulletVelocity(MIN_BULLET_POWER), is(19.7));
        assertThat(Calculations.bulletVelocity(MAX_BULLET_POWER), is(11.0));
    }
}
