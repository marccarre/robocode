package Westminster.Team5.marccarre;

import org.junit.Test;
import robocode.ScannedRobotEvent;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class EnemiesTrackerTest {

	@Test
	public void enemiesTrackerShouldTrackEvents() {
		EnemiesTracker tracker = new EnemiesTracker();
		assertThat(tracker.size(), is(0));
		assertThat(tracker.latest(), is(nullValue()));

		tracker.update(new ScannedRobotEvent("Robot#1", 1.0, 2.0, 3.0, 4.0, 5.0));
		assertThat(tracker.size(), is(1));
		assertThat(tracker.latest(), is(not(nullValue())));
		assertThat(tracker.latest(), is(new State("Robot#1", 1.0, 114.59155902616465, 3.0, 229.1831180523293, 5.0)));
	}

	@Test
	public void enemiesTrackerShouldEvictOldEntries() {
		int stateHistoryMaxSize = 3;

		EnemiesTracker tracker = new EnemiesTracker(stateHistoryMaxSize);
		assertThat(tracker.size(), is(0));

		tracker.update(new ScannedRobotEvent("Robot#1", 1.0, 2.0, 3.0, 4.0, 5.0));
		tracker.update(new ScannedRobotEvent("Robot#1", 2.0, 3.0, 4.0, 5.0, 6.0));
		tracker.update(new ScannedRobotEvent("Robot#1", 3.0, 4.0, 5.0, 6.0, 7.0));
		assertThat(tracker.size(), is(stateHistoryMaxSize));
		assertThat(tracker.latest(), is(new State("Robot#1", 3.0, 229.1831180523293, 5.0, 343.77467707849394, 7.0)));

		tracker.update(new ScannedRobotEvent("Robot#1", 4.0, 5.0, 6.0, 7.0, 8.0));
		assertThat(tracker.size(), is(stateHistoryMaxSize)); // First event has been evicted.
		assertThat(tracker.latest(), is(new State("Robot#1", 4.0, 286.4788975654116, 6.0, 401.07045659157626, 8.0)));
	}

}
