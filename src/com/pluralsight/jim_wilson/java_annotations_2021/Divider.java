package com.pluralsight.jim_wilson.java_annotations_2021;

@CommandKeyword("divide")
public class Divider implements MathProcessing {
	@Override
	public double doCalculation(double leftVal, double rightVal) {
		return leftVal / rightVal;
	}
}
