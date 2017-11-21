package com.elvenrings.tests;

import com.elvenrings.utils.Validator;

public class Tester1
{

	public static void main(String[] args)
	{
		Validator validator = new Validator();
		String givenLetters = " aX";
		String num = "-9";

		if (!validator.onlyLetters(givenLetters))
		{
			System.out.println(validator.getMessage());
		} else
		{
			System.out.println("valid input");
		}

		if (!validator.onlyPositiveDigits(num))
		{
			System.out.println(validator.getMessage());
		} else
		{
			System.out.println("valid input");
		}
	}

}
