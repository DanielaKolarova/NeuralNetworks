/**
 * Copyright 2010 Neuroph Project http://neuroph.sourceforge.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.neuralnet.core.function.activation;

/**
 * Sigmoid activation function. Calculation is based on: 
 * 
 * y = 1/(1+ e^(-slope*x))
 * 
 */
public class SigmoidActivationFunction implements ActivationFunction {

	/**
	 * Slope parameter
	 */
	private double slope = 1d;

	/**
	 * Creates a Sigmoid function with a slope parameter.
	 * 
	 * @param slope
	 *            slope parameter to be set
	 */
	public SigmoidActivationFunction(double slope) {
		this.slope = slope;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double calculateOutput(double summedInput) {
		double denominator = 1 + Math.exp(-slope * summedInput);

		return (1d / denominator);
	}

	@Override
	public double calculateDerivative(double totalInput) {
		// TODO Auto-generated method stub
		return 0;
	}

}
