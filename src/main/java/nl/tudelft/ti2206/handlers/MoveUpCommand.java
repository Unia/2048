package nl.tudelft.ti2206.handlers;

import nl.tudelft.ti2206.gameobjects.Grid;

public class MoveUpCommand extends Command {

	public MoveUpCommand(Grid grid) {
		super(grid);
		this.grid.getUndoStack().push(grid.toString());
	}
	
	@Override
	public void execute() {
		tileHandler.moveUp();
		updateAndSpawn();
	}
}