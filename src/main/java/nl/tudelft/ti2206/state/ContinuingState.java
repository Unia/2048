package nl.tudelft.ti2206.state;

import nl.tudelft.ti2206.gameobjects.Grid;
import nl.tudelft.ti2206.handlers.InputHandler;
import nl.tudelft.ti2206.log.Logger;

public class ContinuingState implements GameState{
	
	/** The singleton reference to the Logger instance. */
	private static Logger logger = Logger.getInstance();

	/** Get current class name, used for logging output. */
	private final String className = this.getClass().getSimpleName();
	
	public ContinuingState(){ 
	}
	

	@Override
	public void update(Grid grid) {	
	}


	@Override
	public void update(Grid localgrid, Grid remotegrid) {	
	}

}
