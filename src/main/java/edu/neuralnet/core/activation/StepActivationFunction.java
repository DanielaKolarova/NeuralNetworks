package edu.neuralnet.core.activation;

/**
 * Step neuron activation function, the output y of this activation function is
 * binary, depending on whether the input meets a specified threshold, 0. The
 * "signal" is sent, i.e. the output is set to one, if the activation meets the
 * threshold.
 * 
 */
public class StepActivationFunction implements ActivationFunction {

	/**
	 * Output value if the input is above or equal the threshold
	 */
	private double yAbove = 1d;

	/**
	 * Output value if the input is bellow the threshold
	 */
	private double yBellow = 0d;

	/**
	 * The output of this activation function is binary, depending on whether
	 * the input meets a specified threshold.
	 */
	private double threshold = 0d;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double calculateOutput(double summedInput) {
		if (summedInput >= threshold)
			return yAbove;
		else
			return yBellow;
	}

	@Override
	public double calculateDerivative(double totalInput) {
		return 0;
	}

}