package Westminster.Team5.marccarre;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DirectionTest {
    private final Random random = mock(Random.class);

    @Test
    public void stayStraight() {
        when(random.nextInt(anyInt())).thenReturn(Direction.STRAIGHT.toInt());
        assertThat(Direction.randomize(random), is(Direction.STRAIGHT));
    }

    @Test
    public void turnLeft() {
        when(random.nextInt(anyInt())).thenReturn(Direction.LEFT.toInt());
        assertThat(Direction.randomize(random), is(Direction.LEFT));
    }

    @Test
    public void turnRight() {
        when(random.nextInt(anyInt())).thenReturn(Direction.RIGHT.toInt());
        assertThat(Direction.randomize(random), is(Direction.RIGHT));
    }
}
