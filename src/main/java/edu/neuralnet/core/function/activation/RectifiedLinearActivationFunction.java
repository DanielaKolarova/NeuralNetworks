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
	public double calculateDerivative(double input) {
		 if (input >= 0) {
	            return 1;
		 }
		 
	     return 0;
	}

}
