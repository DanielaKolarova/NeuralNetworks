package edu.neuralnet.core.function.activation;

/**
 * Neural networks activation function interface.
 */
public interface ActivationFunction {

	/**
	 * Performs calculation based on the sum of input neurons output.
	 * 
	 * @param summedInput
	 *            neuron's sum of outputs respectively inputs for the connected
	 *            neuron
	 * 
	 * @return Output's calculation based on the sum of inputs
	 */
	double calculateOutput(double summedInput);

	/**
	 * Performs calculation of function's derivative.
	 * 
	 * @param totalInput
	 *            neuron's total input
	 * 
	 * @return function's derivative calculated based on the total input
	 */
	double calculateDerivative(double totalInput);

}