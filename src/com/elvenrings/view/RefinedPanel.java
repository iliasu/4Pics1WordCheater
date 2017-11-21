package com.elvenrings.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RefinedPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private List<String> list;
	private JTextArea textArea;
	
	public RefinedPanel()
	{
		list = new ArrayList<>();
		textArea = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(210,237));
		
		this.add(scrollPane);
	}
	

	public void setList(List<String> list)
	{
		this.list = list;
	}
	
	public void addToList(String word)
	{
		if(!list.contains(word))
		{
			list.add(word);
		}
	}
	
	public void refresh()
	{
		textArea.setText("");
		for(String word : list)
		{
			textArea.append(word + "\n");
		}
	}
}
