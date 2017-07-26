package edu.neuralnet.core;

/**
 * Represents a connection between two neurons an the associated weight.
 */
public class NeuronsConnection {

	/**
	 * From neuron for this connection (source neuron). This connection is
	 * output connection for from neuron.
	 */
	protected Neuron fromNeuron;

	/**
	 * To neuron for this connection (target, destination neuron) This
	 * connection is input connection for to neuron.
	 */
	protected Neuron toNeuron;

	/**
	 * Connection weight
	 */
	protected double weight;

	/**
	 * Creates a new connection between specified neurons with random weight.
	 *
	 * @param fromNeuron
	 *            neuron to connect from
	 * @param toNeuron
	 *            neuron to connect to
	 */
	public NeuronsConnection(Neuron fromNeuron, Neuron toNeuron) {
		this.fromNeuron = fromNeuron;
		this.toNeuron = toNeuron;
		this.weight = Math.random();
	}

	/**
	 * Creates a new connection to specified neuron with specified weight object
	 *
	 * @param fromNeuron
	 *            neuron to connect from
	 * @param toNeuron
	 *            neuron to connect to
	 * @param weight
	 *            weight for this connection
	 */
	public NeuronsConnection(Neuron fromNeuron, Neuron toNeuron, double weight) {
		this(fromNeuron, toNeuron);
		this.weight = weight;
	}

	/**
	 * Returns weight for this connection
	 *
	 * @return weight for this connection
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Set the weight of the connection.
	 * 
	 * @param weight
	 *            The new weight of the connection to be set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Returns input of this connection - the activation function result
	 * calculated in the input neuron of this connection.
	 *
	 * @return input received through this connection
	 */
	public double getInput() {
		return fromNeuron.calculateOutput();
	}

	/**
	 * Returns the weighted input of this connection
	 *
	 * @return weighted input of the connection
	 */
	public double getWeightedInput() {
		return fromNeuron.calculateOutput() * weight;
	}

	/**
	 * Gets from neuron for this connection
	 * 
	 * @return from neuron for this connection
	 */
	public Neuron getFromNeuron() {
		return fromNeuron;
	}

	/**
	 * Gets to neuron for this connection
	 * 
	 * @return neuron to set as to neuron
	 */
	public Neuron getToNeuron() {
		return toNeuron;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromNeuron == null) ? 0 : fromNeuron.hashCode());
		result = prime * result + ((toNeuron == null) ? 0 : toNeuron.hashCode());
		long temp;
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
		NeuronsConnection other = (NeuronsConnection) obj;
		if (fromNeuron == null) {
			if (other.fromNeuron != null)
				return false;
		} else if (!fromNeuron.equals(other.fromNeuron))
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
