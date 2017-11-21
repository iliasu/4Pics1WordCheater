package com.elvenrings.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.elvenrings.events.ProcessEvent;
import com.elvenrings.events.SelectionEvent;
import com.elvenrings.interfaces.ProcessListener;
import com.elvenrings.interfaces.SelectionListener;

public class ListPanel extends JPanel implements ProcessListener, ListSelectionListener
{
	private static final long serialVersionUID = 1L;

	private JList<String> list;
	private List<SelectionListener> listeners;

	public ListPanel()
	{
		this.list = new JList<>();
		this.list.setListData(new String[] { "No Input Yet" });

		this.listeners = new ArrayList<>();

		this.list.setSize(30, 30);
		this.list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.list.addListSelectionListener(this);

		JScrollPane scrollPane = new JScrollPane(this.list);
		scrollPane.setPreferredSize(new Dimension(230, 335));
		// set borders
		Border innerBorder = BorderFactory.createTitledBorder("Possibilities");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		this.add(scrollPane);
	}

	public void processingDone(ProcessEvent event)
	{
		List<String> newList = event.getList();

		if (newList != null)
		{
			refresh(newList);
		}
	}

	private void refresh(List<String> listData)
	{
		list.setListData(listData.toArray(new String[listData.size()]));
	}

	public void addSelectionListener(SelectionListener listener)
	{
		listeners.add(listener);
	}

	public void removeSelectionListener(SelectionListener listener)
	{
		listeners.remove(listener);
	}

	private void fireNewSelectionEvent(SelectionEvent event)
	{

		for (SelectionListener listener : listeners)
		{
			listener.newSelectionEvent(event);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		Object source = e.getSource();

		if (source == list)
		{
			List<String> selectedItems = list.getSelectedValuesList();

			SelectionEvent event = new SelectionEvent(selectedItems);

			fireNewSelectionEvent(event);
		}
	}

}
