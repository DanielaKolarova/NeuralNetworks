package edu.neuralnet.core.function.activation;

/**
 * In calculus (a branch of mathematics), a differentiable function of one real
 * variable is a function whose derivative exists at each point in its domain.
 */
public interface DifferentiableFunction {

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
