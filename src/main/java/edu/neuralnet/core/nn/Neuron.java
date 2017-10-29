package edu.neuralnet.core.nn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.neuralnet.core.function.activation.ActivationFunction;

/**
 * Represents a neuron model comprised of: </br>
 * <ul>
 * <li>Activation function</li>
 * <li>Input connections</li>
 * </ul>
 */
public class Neuron implements ProcessingUnit {

	/**
	 * Neuron's identifier
	 */
	private final String id;

	/**
	 * Neuron's output
	 */
	private double output;

	private final double bias = 1;

	private Connection biasConnection;

	/**
	 * Collection of neuron's input connections (connections to this neuron)
	 */
	private List<Connection> inputConnections = new ArrayList<>();

	/**
	 * Collection of neuron's output connections (connections from this to other
	 * neurons)
	 */
	private Map<String, Connection> connectionLookup = new HashMap<>();

	/**
	 * Transfer function for this neuron
	 */
	protected ActivationFunction activationFunction;

	/**
	 * Default constructor with id
	 */
	public Neuron(String id) {
		this.id = id;
	}

	/**
	 * Default constructor with activation function
	 */
	public Neuron(String id, ActivationFunction activationFunction) {
		this.id = id;
		this.activationFunction = activationFunction;
	}

	/**
	 * Default constructor with activation function
	 */
	public Neuron(String id, List<ProcessingUnit> inNeurons, ActivationFunction activationFunction) {
		this.id = id;
		this.activationFunction = activationFunction;
		addInConnections(inNeurons);
	}

	/**
	 * Default constructor with activation function
	 */
	public Neuron(String id, List<ProcessingUnit> inNeurons, ProcessingUnit bias,
			ActivationFunction activationFunction) {
		this(id, inNeurons, activationFunction);
		addBiasConnection(bias);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neuralnet.core.nn.ProcessingUnit#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neuralnet.core.nn.ProcessingUnit#calculateOutput()
	 */
	@Override
	public void calculateOutput() {
		double weightedSum = 0;
		for (Connection con : inputConnections) {
			double weight = con.getWeight();
			double previousLayerOutput = con.getFromNeuron().getOutput();

			weightedSum = weightedSum + (weight * previousLayerOutput);
		}

		if (biasConnection != null) {
			weightedSum += (biasConnection.getWeight() * bias);
		}

		output = activationFunction.calculateOutput(weightedSum);
	}

	private void addInConnections(List<ProcessingUnit> inNeurons) {
		for (ProcessingUnit neuron : inNeurons) {
			Connection con = new Connection(RandomGenerator.generateId(), neuron, this);
			inputConnections.add(con);
			connectionLookup.put(neuron.getId(), con);
		}
	}

	@Override
	public Connection getConnection(String neuronId) {
		return connectionLookup.get(neuronId);
	}

	private void addInConnection(ProcessingUnit neuron) {
		Connection con = new Connection(RandomGenerator.generateId(), neuron, this);
		inputConnections.add(con);
	}

	@Override
	public List<Connection> getInputConnections() {
		return inputConnections;
	}

	private void addBiasConnection(ProcessingUnit neuron) {
		Connection con = new Connection(RandomGenerator.generateId(), neuron, this);
		biasConnection = con;
		inputConnections.add(con);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.neuralnet.core.nn.ProcessingUnit#getOutput()
	 */
	@Override
	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inputConnections == null) ? 0 : inputConnections.hashCode());
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
		Neuron other = (Neuron) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inputConnections == null) {
			if (other.inputConnections != null)
				return false;
		} else if (!inputConnections.equals(other.inputConnections))
			return false;
		return true;
	}

}