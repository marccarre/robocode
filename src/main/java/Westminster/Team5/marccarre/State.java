package Westminster.Team5.marccarre;

import java.util.Comparator;

public class State {
	public static final Comparator<State> IS_CLOSER = new Comparator<State>() {
		@Override
		public int compare(final State x, final State y) {
			return (int) (x.distance - y.distance);
		}
	};

	public static final Comparator<State> IS_WEAKER = new Comparator<State>() {
		@Override
		public int compare(final State x, final State y) {
			return (int) (x.energy - y.energy);
		}
	};

	private final double energy;
	private final double bearing;
	private final double distance;
	private final double heading;
	private final double velocity;

	public State(final double energy, final double bearing, final double distance, final double heading, final double velocity) {
		this.energy = energy;
		this.bearing = bearing;
		this.distance = distance;
		this.heading = heading;
		this.velocity = velocity;
	}

	public double energy() {
		return energy;
	}

	public double bearing() {
		return bearing;
	}

	public double distance() {
		return distance;
	}

	public double heading() {
		return heading;
	}

	public double velocity() {
		return velocity;
	}

	public boolean equals(final Object object) {
		if (object == this)
			return true;
		if (object == null)
			return false;
		if (!(object instanceof State))
			return false;
		final State that = (State) object;
		return (this.energy == that.energy) && (this.bearing == that.bearing) && (this.distance == that.distance) && (this.heading == that.heading) && (this.velocity == that.velocity);
	}
}
