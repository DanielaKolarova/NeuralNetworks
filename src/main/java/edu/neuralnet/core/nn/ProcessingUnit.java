package edu.neuralnet.core.nn;

import java.util.List;

/**
 * NeuralNet processing unit
 * 
 */
public interface ProcessingUnit {

	String getId();

	double getOutput();

	void setOutput(double output);

	void calculateOutput();

	Connection getConnection(String neuronId);

	List<Connection> getInputConnections();

}