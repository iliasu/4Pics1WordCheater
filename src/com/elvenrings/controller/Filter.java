package com.elvenrings.controller;

import java.util.Map;

public class Filter
{
	public static boolean filter1(final Map<Character, Integer> map, String word)
	{
		char[] letters = word.toCharArray();

		for (char c : letters)
		{
			if (!map.containsKey(c))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean filter2(int length, String word)
	{
		if (word == null)
		{
			return false;
		} else
		{
			return length == word.length();
		}
	}

	public static boolean filter3(final Map<Character, Integer> map1, final Map<Character, Integer> map2)
	{
		for (Character ch : map2.keySet())
		{
			int count1 = map1.getOrDefault(ch, 0);
			int count2 = map2.getOrDefault(ch, 0);

			if (count2 > count1)
			{
				return false;
			}
		}

		return true;
	}
}
