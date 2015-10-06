package Westminster.Team5.marccarre;

import robocode.ScannedRobotEvent;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class EnemiesTracker {

	private static final int DEFAULT_STATE_HISTORY_MAX_SIZE = 100;

	private final int stateHistoryMaxSize;
	private final Map<String, Deque<State>> gameState = new HashMap<String, Deque<State>>();
	private final SortedSet<State> statesByDistance = new TreeSet<State>(State.IS_CLOSER);

	private State latestState;

	public EnemiesTracker() {
		this(DEFAULT_STATE_HISTORY_MAX_SIZE);
	}

	public EnemiesTracker(final int stateHistoryMaxSize) {
		this.stateHistoryMaxSize = stateHistoryMaxSize;
	}

	public State update(final ScannedRobotEvent e) {
		final Deque<State> states = getOrCreateStatesFor(e.getName());

		final State state = new State(e.getName(), e.getEnergy(), e.getBearing(), e.getDistance(), e.getHeading(), e.getVelocity());
		states.addLast(state);
		statesByDistance.add(state);
		latestState = state;

		evictOldStates(states);
		return state;
	}

	private Deque<State> getOrCreateStatesFor(final String name) {
		Deque<State> states = gameState.get(name);
		if (states == null) {
			states = new LinkedList<State>();
			gameState.put(name, states);
		}
		return states;
	}

	private void evictOldStates(final Deque<State> states) {
		while (states.size() > stateHistoryMaxSize) {
			states.removeFirst();
		}
		while(statesByDistance.size() > stateHistoryMaxSize) {
			statesByDistance.remove(statesByDistance.last());
		}
	}

	public int size() {
		int size = 0;
		for (final Deque<State> states : gameState.values())
			size += states.size();
		return size;
	}

	public State latest() {
		return latestState;
	}

	public State closest() {
		return statesByDistance.first();
	}
}
