package edu.neuralnet.core.function.activation;

public class IdentityActivationFunction implements ActivationFunction {

	@Override
	public double calculateDerivative(double totalInput) {
		return totalInput;
	}

	@Override
	public double calculateOutput(double summedInput) {
		return 1d;
	}

}
