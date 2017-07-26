package edu.neuralnet.core;

import java.util.ArrayList;
import java.util.List;

import edu.neuralnet.core.function.activation.ActivationFunction;
import edu.neuralnet.core.function.input.InputSummingFunction;

/**
 * Represents a neuron model comprised of: </br>
 * <ul>
 * <li>Summing part - input summing function</li>
 * <li>Activation function</li>
 * <li>Input connections</li>
 * <li>Output connections</li>
 * </ul>
 */

public class Neuron {

	/**
	 * Neuron's identifier
	 */
	private String id;

	/**
	 * Collection of neuron's input connections (connections to this neuron)
	 */
	protected List<NeuronsConnection> inputConnections;

	/**
	 * Collection of neuron's output connections (connections from this to other
	 * neurons)
	 */
	protected List<NeuronsConnection> outputConnections;

	/**
	 * Input function for this neuron
	 */
	protected InputSummingFunction inputSummingFunction;

	/**
	 * Transfer function for this neuron
	 */
	protected ActivationFunction activationFunction;

	/**
	 * Default constructor
	 */
	public Neuron() {
		this.inputConnections = new ArrayList<>();
		this.outputConnections = new ArrayList<>();
	}

	/**
	 * Calculates the neuron's output
	 */
	public double calculateOutput() {
		double totalInput = inputSummingFunction.collectOutput(inputConnections);

		return activationFunction.calculateOutput(totalInput);
	}

	/**
	 * Creates an instance of Neuron with the specified input and activation
	 * functions.
	 *
	 * @param inputSummingFunction
	 *            input function for this neuron
	 * @param activationFunction
	 *            activation function for this neuron
	 */
	public Neuron(InputSummingFunction inputFunction, ActivationFunction activationFunction) {
		this.inputSummingFunction = inputFunction;
		this.activationFunction = activationFunction;
		this.inputConnections = new ArrayList<>();
		this.outputConnections = new ArrayList<>();
	}

	/**
	 * Returns true if there are input connections for this neuron, false
	 * otherwise
	 *
	 * @return true if there is input connection, false otherwise
	 */
	public boolean hasInputConnections() {
		return (this.inputConnections.size() > 0);
	}

	public boolean isOutputConnectedTo(Neuron neuron) {
		for (NeuronsConnection connection : outputConnections) {
			if (connection.getToNeuron() == neuron) {
				return true;
			}
		}
		return false;
	}

	private boolean isInputConnectedTo(Neuron neuron) {
		for (NeuronsConnection connection : inputConnections) {
			if (connection.getFromNeuron() == neuron) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds the specified input connection
	 *
	 * @param connection
	 *            Connection object
	 */
	public void addInputConnection(NeuronsConnection connection) {
		if (this.isInputConnectedTo(connection.getFromNeuron())) {
			return;
		}

		inputConnections.add(connection);

		Neuron fromNeuron = connection.getFromNeuron();
		fromNeuron.addOutputConnection(connection);
	}

	/**
	 * Adds input connection with the given weight, from given neuron
	 *
	 * @param fromNeuron
	 *            neuron to connect from
	 * @param weightVal
	 *            connection weight value
	 */
	public void addInputConnection(Neuron fromNeuron, double weightVal) {
		NeuronsConnection connection = new NeuronsConnection(fromNeuron, this, weightVal);
		this.addInputConnection(connection);
	}

	/**
	 * Adds the specified output connection
	 *
	 * @param connection
	 *            output connection to add
	 */
	protected void addOutputConnection(NeuronsConnection connection) {
		if (connection.getFromNeuron() != this) {
			throw new IllegalArgumentException("Cannot add output connection - bad fromNeuron specified!");
		}

		if (isOutputConnectedTo(connection.getToNeuron())) {
			return;
		}

		outputConnections.add(connection);
	}

	/**
	 * Returns input connections for this neuron
	 *
	 * @return input connections of this neuron
	 */
	public final List<NeuronsConnection> getInputConnections() {
		return inputConnections;
	}

	/**
	 * Returns output connections from this neuron
	 *
	 * @return output connections from this neuron
	 */
	public final List<NeuronsConnection> getOutputConnections() {
		return outputConnections;
	}

	protected void removeInputConnection(NeuronsConnection connection) {
		inputConnections.remove(connection);
	}

	protected void removeOutputConnection(NeuronsConnection connection) {
		outputConnections.remove(connection);
	}

	/**
	 * Removes input connection which is connected to specified neuron
	 *
	 * @param fromNeuron
	 *            neuron which is connected as input
	 */
	public void removeInputConnectionFrom(Neuron fromNeuron) {
		for (NeuronsConnection connection : inputConnections) {
			if (connection.getFromNeuron() == fromNeuron) {
				fromNeuron.removeOutputConnection(connection);
				this.removeInputConnection(connection);
				break;
			}
		}
	}

	public void removeOutputConnectionTo(Neuron toNeuron) {
		for (NeuronsConnection connection : outputConnections) {
			if (connection.getToNeuron() == toNeuron) {
				toNeuron.removeInputConnection(connection);
				this.removeOutputConnection(connection);
				break;
			}
		}
	}

	public void removeAllConnections() {
		inputConnections.clear();
		outputConnections.clear();
	}

	/**
	 * Gets input connection from the specified neuron
	 * 
	 * @param fromNeuron
	 *            neuron connected to this neuron as input
	 */
	public NeuronsConnection getConnectionFrom(Neuron fromNeuron) {
		for (NeuronsConnection connection : this.inputConnections) {
			if (connection.getFromNeuron() == fromNeuron)
				return connection;
		}
		return null;
	}

	/**
	 * Initialize weights for all input connections to specified value
	 *
	 * @param value
	 *            the weight value
	 */
	public void initializeWeights(double value) {
		for (NeuronsConnection connection : this.inputConnections) {
			connection.setWeight(value);
		}
	}

	/**
	 * Returns label for this neuron
	 *
	 * @return label for this neuron
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}