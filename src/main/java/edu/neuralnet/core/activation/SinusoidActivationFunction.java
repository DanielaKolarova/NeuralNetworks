package edu.neuralnet.core.activation;

/**
 * Sinusoid activation function. Calculation is based on:
 * 
 * y = sin(x)
 */
public class SinusoidActivationFunction implements ActivationFunction {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double calculateOutput(double summedInput) {
		return Math.sin(summedInput);
	}

	@Override
	public double calculateDerivative(double net) {
		return Math.cos(net);
	}

}
