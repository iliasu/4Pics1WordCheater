package com.elvenrings.utils;

public class Validator
{
	private String message = "";

	public boolean onlyLetters(String givenLetters)
	{
		if (givenLetters == null)
		{
			this.message = "blank input is not valid";
			return false;
		}
		if (givenLetters.matches(""))
		{
			this.message = "blank input is not valid";
			return false;
		} else if (!givenLetters.matches("[a-zA-Z]+"))
		{
			this.message = "input should only contain alphabetic characters";
			return false;
		}
		return true;
	}

	public boolean onlyPositiveDigits(String num)
	{
		if (num == null)
		{
			this.message = "blank input is not valid";
			return false;
		}
		if (num.matches(""))
		{
			this.message = "blank input is not valid";
			return false;
		}
		if (!num.matches("[0-9]+"))
		{
			this.message = "input should only contain digits 0-9";
			return false;
		}

		try
		{
			Integer.parseInt(num);
		} catch (NumberFormatException e)
		{
			this.message = "input should be an integer";
			return false;
		}
		return true;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void clear()
	{
		this.message = "";
	}

}
