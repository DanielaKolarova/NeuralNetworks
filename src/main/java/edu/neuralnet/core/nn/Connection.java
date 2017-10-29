package edu.neuralnet.core.nn;

/**
 * Represents a connection between two neurons an the associated weight.
 */
public class Connection {

	/**
	 * Connection's identifier
	 */
	private final String id;

	/**
	 * From neuron for this connection (source neuron). This connection is
	 * output connection for from neuron.
	 */
	private final ProcessingUnit fromNeuron;

	/**
	 * To neuron for this connection (target, destination neuron) This
	 * connection is input connection for to neuron.
	 */
	private final ProcessingUnit toNeuron;

	/**
	 * Assigned weight
	 */
	private double weight = 0;

	private double deltaWeight = 0;

	private double prevDeltaWeight = 0; // for momentum

	public Connection(String id, ProcessingUnit fromN, ProcessingUnit toN) {
		this.id = id;
		fromNeuron = fromN;
		toNeuron = toN;
	}

	public String getId() {
		return id;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setDeltaWeight(double deltaWeight) {
		this.prevDeltaWeight = this.deltaWeight;
		this.deltaWeight = deltaWeight;
	}

	public double getPrevDeltaWeight() {
		return prevDeltaWeight;
	}

	public ProcessingUnit getFromNeuron() {
		return fromNeuron;
	}

	public ProcessingUnit getToNeuron() {
		return toNeuron;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(deltaWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fromNeuron == null) ? 0 : fromNeuron.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		temp = Double.doubleToLongBits(prevDeltaWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((toNeuron == null) ? 0 : toNeuron.hashCode());
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		if (Double.doubleToLongBits(deltaWeight) != Double.doubleToLongBits(other.deltaWeight))
			return false;
		if (fromNeuron == null) {
			if (other.fromNeuron != null)
				return false;
		} else if (!fromNeuron.equals(other.fromNeuron))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(prevDeltaWeight) != Double.doubleToLongBits(other.prevDeltaWeight))
			return false;
		if (toNeuron == null) {
			if (other.toNeuron != null)
				return false;
		} else if (!toNeuron.equals(other.toNeuron))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

}