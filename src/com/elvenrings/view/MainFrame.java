package com.elvenrings.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.elvenrings.controller.Controller;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private InputPanel inputPanel;
	private Controller controller;
	private ListPanel listPanel;

	public MainFrame(String title)
	{
		super(title);
		this.setResizable(false);

		// create components
		inputPanel = new InputPanel();
		controller = new Controller();
		listPanel = new ListPanel();

		inputPanel.addInputListener(controller);
		controller.addProcessListeners(listPanel);
		listPanel.addSelectionListener(inputPanel);

		this.setLayout(new BorderLayout());
		this.add(inputPanel, BorderLayout.LINE_START);
		this.add(listPanel, BorderLayout.CENTER);

		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
