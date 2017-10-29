package edu.neuralnet.core.nn;

import java.util.Random;
import java.util.UUID;

public class RandomGenerator {

	private static final Random random = new Random();

	private static final int randomWeightMultiplier = 1;

	private RandomGenerator() {
		throw new AssertionError();
	}

	public static String generateId() {
		return UUID.randomUUID().toString();
	}

	public static double getRandom() {
		double randomValue = randomWeightMultiplier * (random.nextDouble() * 2 - 1); // [-1;1]
		System.out.println("randomValue = " + randomValue);
		return randomValue;
	}

}
