package com.elvenrings.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import com.elvenrings.events.InputEvent;
import com.elvenrings.events.ProcessEvent;
import com.elvenrings.interfaces.InputListener;
import com.elvenrings.interfaces.ProcessListener;

public class Controller implements InputListener
{
	private List<ProcessListener> processListeners;

	public Controller()
	{
		processListeners = new ArrayList<>();
	}

	public void InputEventOccurred(InputEvent event) throws FileNotFoundException, IOException
	{
		String letters = event.getLetters();
		String num = event.getNumber();

		SwingWorker<List<String>, Void> worker = new SwingWorker<List<String>, Void>() {

			@Override
			protected List<String> doInBackground() throws Exception
			{
				Processor processor = new Processor(letters, num);

				List<String> matches = processor.process();

				return matches;
			}

			@Override
			protected void done()
			{
				try
				{
					List<String> matches = this.get();
					ProcessEvent ev = new ProcessEvent(matches);
					fireProcessDoneEvent(ev);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		worker.execute();

	}

	public void fireProcessDoneEvent(ProcessEvent event)
	{
		for (ProcessListener listener : processListeners)
		{
			listener.processingDone(event);
		}
	}

	public void addProcessListeners(ProcessListener listener)
	{
		processListeners.add(listener);
	}

	public void removeProcessListeners(ProcessListener listener)
	{
		processListeners.remove(listener);
	}

}
