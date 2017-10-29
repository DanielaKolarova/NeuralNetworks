package edu.neuralnet.core.nn;

import java.util.ArrayList;
import java.util.List;

import edu.neuralnet.core.function.activation.ActivationFunction;
import edu.neuralnet.core.function.activation.IdentityActivationFunction;
import edu.neuralnet.core.function.activation.SigmoidActivationFunction;

public class NeuralNet {

	private final List<ProcessingUnit> inputLayer = new ArrayList<>();

	private final List<ProcessingUnit> hiddenLayer = new ArrayList<>();

	private final List<ProcessingUnit> outputLayer = new ArrayList<>();
	
	private final ProcessingUnit bias = new Neuron("BIAS");

	private final double learningRate = 2.9f;

	private final double momentum = 0.7f;

	/**
	 * XOR inputs
	 */
	private final double inputs[][] = { { 1, 1 }, { 1, 0 }, { 0, 1 }, { 0, 0 } };

	/**
	 * XOR expected outputs
	 */
	private final double expectedOutputs[][] = { { 0 }, { 1 }, { 1 }, { 0 } };

	/**
	 * Result output with initial values
	 */
	private double resultOutputs[][] = { { -1 }, { -1 }, { -1 }, { -1 } };

	// private double output[];

	public static void main(String[] args) {
		NeuralNet neuralNet = new NeuralNet(2, 4, 1, new SigmoidActivationFunction());
		int maxRuns = 50000;
		double minErrorCondition = 0.001;
		neuralNet.train(maxRuns, minErrorCondition);
	}

	public NeuralNet(int numberOfInputNeurons, int numberOfHiddenNeurons, int numberOfOutputNeurons,
			ActivationFunction activationFunction) {

		for (int j = 0; j < numberOfInputNeurons; j++) {
			ProcessingUnit neuron = new Neuron(RandomGenerator.generateId(), new IdentityActivationFunction());
			inputLayer.add(neuron);
		}

		for (int j = 0; j < numberOfHiddenNeurons; j++) {
			ProcessingUnit neuron = new Neuron(RandomGenerator.generateId(), inputLayer, bias, activationFunction);
			hiddenLayer.add(neuron);
		}

		for (int j = 0; j < numberOfOutputNeurons; j++) {
			Neuron neuron = new Neuron(RandomGenerator.generateId(), hiddenLayer, bias, activationFunction);
			outputLayer.add(neuron);
		}

		for (ProcessingUnit neuron : hiddenLayer) {
			List<Connection> connections = neuron.getInputConnections();
			for (Connection conn : connections) {
				conn.setWeight(RandomGenerator.getRandom());
			}
		}
		for (ProcessingUnit neuron : outputLayer) {
			List<Connection> connections = neuron.getInputConnections();
			for (Connection conn : connections) {
				conn.setWeight(RandomGenerator.getRandom());
			}
		}
	}

	/**
	 * 
	 * @param inputs
	 *            There is equally many neurons in the input layer as there are
	 *            in input variables
	 */
	public void setInput(double inputs[]) {
		for (int i = 0; i < inputLayer.size(); i++) {
			inputLayer.get(i).setOutput(inputs[i]);
		}
	}

	public double[] getOutput() {
		double[] outputs = new double[outputLayer.size()];
		for (int i = 0; i < outputLayer.size(); i++)
			outputs[i] = outputLayer.get(i).getOutput();
		return outputs;
	}

	/**
	 * Calculate the output of the neural network based on the input, the
	 * forward operation
	 */
	public void activate() {
		for (ProcessingUnit neuron : hiddenLayer)
			neuron.calculateOutput();
		for (ProcessingUnit neuron : outputLayer)
			neuron.calculateOutput();
	}

	public List<ProcessingUnit> getInputLayer() {
		return inputLayer;
	}

	public List<ProcessingUnit> getHiddenLayer() {
		return hiddenLayer;
	}

	public List<ProcessingUnit> getOutputLayer() {
		return outputLayer;
	}

	public double[][] getResultOutputs() {
		return resultOutputs;
	}

	public void setResultOutputs(double[][] resultOutputs) {
		this.resultOutputs = resultOutputs;
	}

	/**
	 * Our goal with backpropagation is to update each of the weights in the
	 * network so that they cause the actual output to be closer the target
	 * output, thereby minimizing the error for each output neuron and the
	 * network as a whole. Calculate the partial derivative of the error with
	 * respect to each of the weight leading into the output neurons bias is
	 * also updated here.
	 * 
	 * @param expectedOutput
	 *            neural net's expected output
	 * 
	 */
	private void applyBackpropagation(double expectedOutput[]) {
		int i = 0;
		for (ProcessingUnit neuron : outputLayer) {
			List<Connection> connections = neuron.getInputConnections();
			for (Connection con : connections) {
				double ak = neuron.getOutput();
				double ai = con.getFromNeuron().getOutput();
				double desiredOutput = expectedOutput[i];

				double partialDerivative = -ak * (1 - ak) * ai * (desiredOutput - ak);
				double deltaWeight = -learningRate * partialDerivative;
				double newWeight = con.getWeight() + deltaWeight;
				con.setDeltaWeight(deltaWeight);
				con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
			}
			i++;
		}

		for (ProcessingUnit neuron : hiddenLayer) {
			List<Connection> connections = neuron.getInputConnections();
			for (Connection con : connections) {
				double aj = neuron.getOutput();
				double ai = con.getFromNeuron().getOutput();
				double sumKoutputs = 0;
				int j = 0;
				for (ProcessingUnit outputNeuron : outputLayer) {
					double wjk = outputNeuron.getConnection(neuron.getId()).getWeight();
					double desiredOutput = (double) expectedOutput[j];
					double ak = outputNeuron.getOutput();
					j++;
					sumKoutputs = sumKoutputs + (-(desiredOutput - ak) * ak * (1 - ak) * wjk);
				}

				double partialDerivative = aj * (1 - aj) * ai * sumKoutputs;
				double deltaWeight = -learningRate * partialDerivative;
				double newWeight = con.getWeight() + deltaWeight;
				con.setDeltaWeight(deltaWeight);
				con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
			}
		}
	}

	private void train(int maxSteps, double minError) {
		int i;
		double error = 1;
		for (i = 0; i < maxSteps && error > minError; i++) {
			error = 0;
			for (int p = 0; p < inputs.length; p++) {
				setInput(inputs[p]);

				activate();

				double output[] = getOutput();
				resultOutputs[p] = output;

				for (int j = 0; j < expectedOutputs[p].length; j++) {
					double err = Math.pow(output[j] - expectedOutputs[p][j], 2);
					error += err;
				}

				applyBackpropagation(expectedOutputs[p]);
			}
		}

		printResult(inputs);

		System.out.println("Sum of squared errors = " + error);
		System.out.println("EPOCH " + i + "\n");
		if (i == maxSteps) {
			System.out.println("Error in training, try again!");
		}
	}

	private void printResult(double inputs[][]) {
		System.out.println("Multilayer perceptron with XOR training");
		for (int p = 0; p < inputs.length; p++) {
			System.out.print("INPUTS: ");
			for (int x = 0; x < inputLayer.size(); x++) {
				System.out.print(inputs[p][x] + " ");
			}

			System.out.print("EXPECTED: ");
			for (int x = 0; x < outputLayer.size(); x++) {
				System.out.print(expectedOutputs[p][x] + " ");
			}

			System.out.print("ACTUAL: ");
			for (int x = 0; x < outputLayer.size(); x++) {
				System.out.print(resultOutputs[p][x] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
