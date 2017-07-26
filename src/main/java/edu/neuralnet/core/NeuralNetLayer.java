package edu.neuralnet.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Neural networks can be composed of several linked layers, forming the
 * so-called multilayer networks. A layer can be defined as a set of neurons
 * comprising a single neural net's layer.
 */
public class NeuralNetLayer {

	/**
	 * Layer's identifier
	 */
	private String id;

	/**
	 * Collection of neurons in this layer
	 */
	protected List<Neuron> neurons;

	/**
	 * Creates an empty layer
	 */
	public NeuralNetLayer(String id) {
		this.id = id;
		neurons = new ArrayList<>();
	}

	/**
	 * Creates an an empty layer for specified number of neurons
	 * 
	 * @param neuronsCount
	 *            number of neurons in this layer
	 */
	public NeuralNetLayer(String id, int neuronsCount) {
		this.id = id;
		neurons = new ArrayList<>(neuronsCount);
	}
	
	/**
	 * Creates a layer with the specified number of neurons with
	 * specified neuron properties
	 *
	 * @param neuronsCount
	 *            number of neurons in layer
	 * @param neuronProperties
	 *            properties of neurons in layer
	 */
	public NeuralNetLayer(String id, List<Neuron> neurons) {
		this.id = id;
		this.neurons = neurons;
	}

	/**
	 * Returns the list of neurons of this layer
	 *
	 * @return list of neurons in this layer
	 */
	public final List<Neuron> getNeurons() {
		return neurons;
	}

	/**
	 * Adds specified neuron to this layer
	 *
	 * @param neuron
	 *            neuron to add
	 */
	public final void addNeuron(Neuron neuron) {
		neurons.add(neuron);
	}

	/**
	 * Returns neuron at specified index position in this layer
	 *
	 * @param index
	 *            neuron index position
	 * @return neuron at specified index position
	 */
	public Neuron getNeuronAt(int index) {
		return neurons.get(index);
	}

	/**
	 * Returns number of neurons in this layer
	 *
	 * @return number of neurons in this layer
	 */
	public int getNeuronsCount() {
		return neurons.size();
	}

	/**
	 * Get layer id
	 *
	 * @return layer id
	 */
	public String getId() {
		return id;
	}

}