package com.elvenrings.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Processor
{
	private String letters;
	private String num;

	Processor(String letters, String num)
	{
		this.letters = letters;
		this.num = num;
	}

	public List<String> process() throws FileNotFoundException, IOException
	{
		Map<Character, Integer> letterMap = getLetterMap(this.letters);
		ArrayList<String> result = new ArrayList<>();

		File file = new File("bin/com/elvenrings/data/wordlist.txt");

		try (FileReader reader = new FileReader(file); Scanner scanner = new Scanner(reader))
		{
			while (scanner.hasNext())
			{
				String line = scanner.nextLine();
				String word = line.toLowerCase();

				if (Filter.filter1(letterMap, word))
				{
					if (Filter.filter2(Integer.parseInt(this.num), word))
					{
						if (Filter.filter3(letterMap, getLetterMap(word)))
						{
							result.add(word);
						}
					}
				}
			}
		}

		return result;
	}

	private Map<Character, Integer> getLetterMap(String letters)
	{
		Map<Character, Integer> map = new HashMap<>();

		for (Character ch : letters.toCharArray())
		{
			ch = Character.toLowerCase(ch);
			Integer count = map.getOrDefault(ch, 0);
			map.put(ch, count + 1);
		}
		return map;
	}

}
