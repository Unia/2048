package nl.tudelft.ti2206.state;

import nl.tudelft.ti2206.handlers.InputHandler;

public class RunningState implements GameState{
	
	private InputHandler inputhandler;
	
	public RunningState(InputHandler handler){
		inputhandler = handler; 
	}	
	
	@Override
	public void update() {
		
	}
}
