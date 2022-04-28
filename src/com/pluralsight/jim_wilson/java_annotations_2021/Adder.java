package com.pluralsight.jim_wilson.java_annotations_2021;

@CommandKeyword("add")
public class Adder implements MathProcessing {
	@Override
	public double doCalculation(double leftVal, double rightVal) {
		return leftVal + rightVal;
	}
}
