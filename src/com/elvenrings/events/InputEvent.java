package com.elvenrings.events;

public class InputEvent
{
	private String letters;
	private String number;

	public InputEvent(String letters, String number)
	{
		this.letters = letters;
		this.number = number;
	}

	public String getLetters()
	{
		return letters;
	}

	public String getNumber()
	{
		return number;
	}

}
