package com.elvenrings.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.elvenrings.events.InputEvent;

public interface InputListener
{
	public void InputEventOccurred(InputEvent event) throws FileNotFoundException, IOException;
}
