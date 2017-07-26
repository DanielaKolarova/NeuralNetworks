package edu.neuralnet.core.function.input;

import java.util.List;

import edu.neuralnet.core.NeuronsConnection;

/**
 * Calculates the weighted sums of the input neurons' outputs.
 */
public final class WeightedSumFunction implements InputSummingFunction {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double collectOutput(List<NeuronsConnection> inputConnections) {
		double weightedSum = 0d;
		for (NeuronsConnection connection : inputConnections) {
			weightedSum += connection.getWeightedInput();
		}

		return weightedSum;
	}

}
