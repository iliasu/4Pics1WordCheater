package com.elvenrings.app;

import javax.swing.SwingUtilities;

import com.elvenrings.view.MainFrame;

public class App
{

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				new MainFrame("4 pics 1 word cheater");
			}
		});
	}

}
