package com.pluralsight.jim_wilson.java_annotations_2021;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
	private static double handleCalculation(
		Object processor,
		double leftVal,
		double rightVal
	) {
		double result = 0.0d;

		try {
			CommandKeyword ck = processor.getClass().getAnnotation(CommandKeyword.class);

			String methodName = ck.method();

			Method processingMethod = processor.getClass().getMethod(
				methodName,
				double.class,
				double.class
			);

			result = (double) processingMethod.invoke(processor, leftVal, rightVal);
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		return result;
	}

	private static void process(
		String keyword,
		double leftVal,
		double rightVal
	) {
		Object processor = retrieveProcessor(keyword);

		double result = 0.0d;

		if(processor instanceof MathProcessing) {
			result = ((MathProcessing) processor).doCalculation(leftVal, rightVal);
		}else {
			result = handleCalculation(processor, leftVal, rightVal);
		}

		System.out.println("result: " + result);
	}

	private static Object retrieveProcessor(String keyword) {
		Object[] mathProcessors = {
			new Adder(),
			new Subtracter(),
			new Multiplier(),
			new Divider(),
			new PowerOf()
		};

		for(Object processor : mathProcessors) {
			CommandKeyword ck = processor.getClass().getAnnotation(CommandKeyword.class);

			String nameElement = ck.value();

			if(keyword.equalsIgnoreCase(nameElement)) {
				return processor;
			}
		}

		return null;
	}

	static double getValueFromWord(String word) {
		double value = -1d;

		String[] numberWords = {
			"zero", "one",
			"two", "three",
			"four", "five",
			"six", "seven",
			"eight", "nine"
		};

		for(int i = 0; i < numberWords.length; i++) {
			if(word.equals(numberWords[i])) {
				value = i;

				break;
			}
		}

		if(value == -1d) {
			value = Double.parseDouble(word);
		}

		return value;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter an operation & 2 numbers: ");
		String userInput = scanner.nextLine();

		String[] parts = userInput.split(" ");

		String keyword = parts[0];

		double leftVal = getValueFromWord(parts[1]);
		double rightVal = getValueFromWord(parts[2]);

		process(keyword, leftVal, rightVal);

		scanner.close();
	}
}
