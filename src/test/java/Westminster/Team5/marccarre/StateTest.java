package Westminster.Team5.marccarre;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class StateTest {

	@Test
	public void stateShouldHoldProvidedValues() {
		final double energy = 1.0;
		final double bearing = 2.0;
		final double distance = 3.0;
		final double heading = 4.0;
		final double velocity = 5.0;
		State state = new State(energy, bearing, distance, heading, velocity);
		assertThat(state.energy(), is(energy));
		assertThat(state.bearing(), is(bearing));
		assertThat(state.distance(), is(distance));
		assertThat(state.heading(), is(heading));
		assertThat(state.velocity(), is(velocity));
	}

	@Test
	public void isCloserShouldSortByAscendingDistance() {
		State x = new State(0, 0, 100, 0, 0);
		State y = new State(0, 0, 200, 0, 0);
		assertThat(State.IS_CLOSER.compare(x, y), is(lessThan(0)));

		PriorityQueue<State> pq = new PriorityQueue<State>(State.IS_CLOSER);
		pq.add(x);
		pq.add(y);
		assertThat(pq.peek(), is(x));
	}

	@Test
	public void isWeakerShouldSortByAscendingDistance() {
		State x = new State(100, 0, 0, 0, 0);
		State y = new State(200, 0, 0, 0, 0);
		assertThat(State.IS_WEAKER.compare(x, y), is(lessThan(0)));

		PriorityQueue<State> pq = new PriorityQueue<State>(State.IS_WEAKER);
		pq.add(x);
		pq.add(y);
		assertThat(pq.peek(), is(x));
	}

}
