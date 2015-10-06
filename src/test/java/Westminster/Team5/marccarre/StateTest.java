package Westminster.Team5.marccarre;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class StateTest {
    private final String name = "Robot#1";
    private final double energy = 1.0;
    private final double bearing = 2.0;
    private final double distance = 3.0;
    private final double heading = 4.0;
    private final double velocity = 5.0;

    @Test
    public void stateShouldHoldProvidedValues() {
        State state = new State(name, energy, bearing, distance, heading, velocity);
        assertThat(state.name(), is(name));
        assertThat(state.energy(), is(energy));
        assertThat(state.bearing(), is(bearing));
        assertThat(state.distance(), is(distance));
        assertThat(state.heading(), is(heading));
        assertThat(state.velocity(), is(velocity));
    }

    @Test
    public void stateShouldPrintNicely() {
        assertThat(new State(name, energy, bearing, distance, heading, velocity).toString(), is("State{name:Robot#1, energy:1.0, bearing:2.0, distance: 3.0, heading:4.0, velocity:5.0}"));
    }

    @Test
    public void isCloserShouldSortByAscendingDistance() {
        State x = new State("Robot#1", 0, 0, 100, 0, 0);
        State y = new State("Robot#1", 0, 0, 200, 0, 0);
        assertThat(State.IS_CLOSER.compare(x, y), is(lessThan(0)));

        PriorityQueue<State> pq = new PriorityQueue<State>(State.IS_CLOSER);
        pq.add(x);
        pq.add(y);
        assertThat(pq.peek(), is(x));

        SortedSet<State> set = new TreeSet<State>(State.IS_CLOSER);
        set.add(x);
        set.add(y);
        assertThat(set.first(), is(x));
        assertThat(set.last(), is(y));
    }

    @Test
    public void isWeakerShouldSortByAscendingDistance() {
        State x = new State("Robot#1", 100, 0, 0, 0, 0);
        State y = new State("Robot#1", 200, 0, 0, 0, 0);
        assertThat(State.IS_WEAKER.compare(x, y), is(lessThan(0)));

        PriorityQueue<State> pq = new PriorityQueue<State>(State.IS_WEAKER);
        pq.add(x);
        pq.add(y);
        assertThat(pq.peek(), is(x));

        SortedSet<State> set = new TreeSet<State>(State.IS_WEAKER);
        set.add(x);
        set.add(y);
        assertThat(set.first(), is(x));
        assertThat(set.last(), is(y));
    }

}
