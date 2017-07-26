package edu.neuralnet.core;

import java.util.List;

/**
 * Represents an artificial neural network with layers containing neurons.
 */
public class NeuralNet {

	/**
	 * Neural network id
	 */
	private String id;

	/**
	 * Neural network input layer
	 */
	private NeuralNetLayer inputLayer;

	/**
	 * Neural network hidden layers
	 */
	private List<NeuralNetLayer> hiddenLayers;

	/**
	 * Neural network output layer
	 */
	private NeuralNetLayer outputLayer;

	/**
	 * Constructs a neural net with all layers present.
	 * 
	 * @param id
	 *            Neural network id to be set
	 * @param inputLayer
	 *            Neural network input layer to be set
	 * @param hiddenLayers
	 *            Neural network hidden layers to be set
	 * @param outputLayer
	 *            Neural network output layer to be set
	 */
	public NeuralNet(String id, NeuralNetLayer inputLayer, List<NeuralNetLayer> hiddenLayers,
			NeuralNetLayer outputLayer) {
		this.id = id;
		this.inputLayer = inputLayer;
		this.hiddenLayers = hiddenLayers;
		this.outputLayer = outputLayer;
	}

	/**
	 * Constructs a neural net without hidden layers.
	 * 
	 * @param id
	 *            Neural network id to be set
	 * @param inputLayer
	 *            Neural network input layer to be set
	 * @param outputLayer
	 *            Neural network output layer to be set
	 */
	public NeuralNet(String id, NeuralNetLayer inputLayer, NeuralNetLayer outputLayer) {
		this.id = id;
		this.inputLayer = inputLayer;
		this.outputLayer = outputLayer;
	}

	public String getId() {
		return id;
	}

	public NeuralNetLayer getInputLayer() {
		return inputLayer;
	}

	public List<NeuralNetLayer> getHiddenLayers() {
		return hiddenLayers;
	}

	public NeuralNetLayer getOutputLayer() {
		return outputLayer;
	}

}
