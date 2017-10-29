package edu.neuralnet.neuroph;

import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

/**
 * Create and train a simple multi-layered
 * perceptron for XOR problem.
 * 
 */
public class XorMLPNeuroph {

	public static void main(String[] args) {

		DataSet trainingSet = new DataSet(2, 1);
		trainingSet.addRow(new DataSetRow(new double[] { 0, 0 }, new double[] { 0 }));
		trainingSet.addRow(new DataSetRow(new double[] { 0, 1 }, new double[] { 1 }));
		trainingSet.addRow(new DataSetRow(new double[] { 1, 0 }, new double[] { 1 }));
		trainingSet.addRow(new DataSetRow(new double[] { 1, 1 }, new double[] { 0 }));

		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 2, 3, 1);

		myMlPerceptron.learn(trainingSet);

		System.out.println("Testing trained neural network");
		testNeuralNetwork(myMlPerceptron, trainingSet);
	}

	public static void testNeuralNetwork(NeuralNetwork<BackPropagation> nnet, DataSet testSet) {

		for (DataSetRow dataRow : testSet.getRows()) {
			nnet.setInput(dataRow.getInput());
			nnet.calculate();
			double[] networkOutput = nnet.getOutput();
			System.out.print("INPUT: " + Arrays.toString(dataRow.getInput()));
			System.out.println(" OUTPUT: " + Arrays.toString(networkOutput));
		}

	}

}
