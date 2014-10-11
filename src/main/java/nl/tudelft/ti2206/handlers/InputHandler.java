package nl.tudelft.ti2206.handlers;

import nl.tudelft.ti2206.gameobjects.Grid;
import nl.tudelft.ti2206.log.Logger;
import nl.tudelft.ti2206.screens.menuscreens.MenuScreen;
import nl.tudelft.ti2206.solver.Solver;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * The InputHandler processes input events, from for example the keyboard or the
 * mice. Currently, only the keyboard is needed.
 */
public class InputHandler extends InputListener {
	/**
	 * A reference to the current Grid, so the called objects can interact with
	 * it.
	 */
	private Grid grid;

	/** The solver to automatically solve this game. */
	private Solver solver;

	/** The singleton reference to the Logger instance. */
	private static Logger logger = Logger.getInstance();

	/** Get current class name, used for logging output. */
	private final String className = this.getClass().getSimpleName();

//	Timer solver = null;
//	private Benchmark bmark;

	private Command command;

	/**
	 * Creates a new InputHandler instance.
	 * 
	 * @param grid
	 *            A reference to the current Grid.
	 */
	public InputHandler(Grid grid) {
		this.grid = grid;
	}

	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		switch (keycode) {
		case Keys.DPAD_DOWN:
			logger.debug(className, "User pressed key: DOWN");
			setCommand(new MoveDownCommand(grid));
			command.execute();
			return true;
		case Keys.DPAD_UP:
			logger.debug(className, "User pressed key: UP");
			setCommand(new MoveUpCommand(grid));
			command.execute();
			return true;
		case Keys.DPAD_LEFT:
			logger.debug(className, "User pressed key: LEFT");
			setCommand(new MoveLeftCommand(grid));
			command.execute();
			return true;
		case Keys.DPAD_RIGHT:
			logger.debug(className, "User pressed key: RIGHT");
			setCommand(new MoveRightCommand(grid));
			command.execute();
			return true;
		case Keys.ESCAPE:
			logger.debug(className, "User pressed key: ESCAPE");
			ProgressHandler.getInstance().saveGame(grid);
			ScreenHandler.getInstance().add(new MenuScreen());
			return true;
//		case Keys.S:
//			if (bmark == null) {
//				logger.debug(className,
//						"Solving this grid! At least, trying to...");
//				bmark = new Benchmark(grid, Strategy.HUMAN, 1, 100, 6);
//				bmark.start();
//			} else if (bmark.isRunning()) {
//				bmark.stop();
//				bmark = null;
//				logger.debug(className, "Autosolver stopped.");
//			}
//			return true;
		case Keys.R:
			if (grid != null) {
				grid.restart();
			}
			return true;
		case Keys.S:
			if (solver == null) {
				solver = new Solver(grid, 1);
				solver.solve();
			} else {
				solver.cancel();
				solver = null;
			}
			return true;
		}
		return false;
	}
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		return command;
	}
}
