package nl.tudelft.ti2206.handlers;

import nl.tudelft.ti2206.gameobjects.Grid;

public class MoveLeftCommand extends Command {

	public MoveLeftCommand(Grid grid) {
		super(grid);
		this.grid.getUndoStack().push(grid.toString());
	}

	@Override
	public void execute() {
		tileHandler.moveLeft();
		updateAndSpawn();
	}
}
