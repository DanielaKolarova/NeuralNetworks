package edu.neuralnet.core.function.activation;

/**
 * Rectified Linear activation function
 */
public class RectifiedLinearActivationFunction implements ActivationFunction {

	/**
	 * {@inheritDoc}
	 */
    @Override
    public double calculateOutput(double summedInput) {
        return Math.max(0, summedInput);
    }

	@Override
	public double calculateDerivative(double totalInput) {
		// TODO Auto-generated method stub
		return 0;
	}

}
