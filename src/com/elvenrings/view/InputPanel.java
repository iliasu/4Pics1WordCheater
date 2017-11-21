package com.elvenrings.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.elvenrings.events.InputEvent;
import com.elvenrings.events.SelectionEvent;
import com.elvenrings.interfaces.InputListener;
import com.elvenrings.interfaces.SelectionListener;
import com.elvenrings.utils.Validator;

public class InputPanel extends JPanel implements ActionListener, SelectionListener
{
	private static final long serialVersionUID = 1L;

	private JTextField letterField;
	private JTextField numField;
	private JLabel letterLabel;
	private JLabel numLabel;
	private JButton processButton;
	private List<InputListener> inputListeners;
	private RefinedPanel refinedPanel;
	
	private Validator validator;
	
	public InputPanel()
	{
		//create components
		this.letterField = new JTextField(10);
		this.numField = new JTextField(5);
		this.letterLabel = new JLabel("Given letters:");
		this.numLabel = new JLabel("No. of letters:");
		this.processButton = new JButton("Process");
		this.inputListeners = new ArrayList<>();
		
		this.refinedPanel = new RefinedPanel();
		//create helper components
		validator = new Validator();
		
		//set listeners
		this.processButton.addActionListener(this);
		
		//set borders
		Border innerBorder = BorderFactory.createTitledBorder("Inputs");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		//layout components
		layoutComponents();
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if(source == this.processButton)
		{
			String givenLetters = letterField.getText();
			if(!validator.onlyLetters(givenLetters))
			{
				System.err.println(validator.getMessage());
				JOptionPane.showMessageDialog(InputPanel.this, validator.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//not really important - clear message field of validator
			validator.clear();
			
			String num = numField.getText();
			if(!validator.onlyPositiveDigits(num))
			{
				System.err.println(validator.getMessage());
				JOptionPane.showMessageDialog(InputPanel.this, validator.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			validator.clear();
			
			InputEvent event = new InputEvent(givenLetters, num);
			
			fireInputEvent(event);
			
		}
	}
	
	private void layoutComponents()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.NONE;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 0;
		gc.gridy = 0;
		this.add(letterLabel, gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		this.add(letterField, gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 0;
		gc.gridy = 1;
		this.add(numLabel, gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		this.add(numField, gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 2;
		this.add(processButton, gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 2;
		this.add(new JLabel("Refined List:"), gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weighty = 10.0;
		gc.gridwidth = 2;
		this.add(refinedPanel, gc);
	}
	
	public void addInputListener(InputListener listener)
	{
		this.inputListeners.add(listener);
	}
	
	public void removeInputListener(InputListener listener)
	{
		this.inputListeners.remove(listener);
	}
	private void fireInputEvent(InputEvent event)
	{
		for(InputListener listener : inputListeners)
		{
			try
			{
				listener.InputEventOccurred(event);
				
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void newSelectionEvent(SelectionEvent event)
	{
		List<String> list = event.getList();
		refinedPanel.setList(list);
		refinedPanel.refresh();
	}
}
