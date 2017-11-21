package com.elvenrings.events;

import java.util.List;

public class ProcessEvent
{
	private List<String> list;

	public ProcessEvent(List<String> list)
	{
		this.list = list;
	}

	public List<String> getList()
	{
		return list;
	}

}
