package com.elvenrings.events;

import java.util.List;

public class SelectionEvent
{
	private List<String> list;

	
	public SelectionEvent(List<String> list)
	{
		this.list = list;
	}


	public List<String> getList()
	{
		return list;
	}
	
}
