package nl.tudelft.ti2206.utils.ai;

import java.util.Timer;
import java.util.TimerTask;

import nl.tudelft.ti2206.gameobjects.Grid;
import nl.tudelft.ti2206.gameobjects.Grid.Direction;
import nl.tudelft.ti2206.utils.log.Logger;

public class GridSolver extends TimerTask {

	public enum Strategy {
		HUMAN
	}

	private static int succesfulMoves;

	private Timer timer;

	private int delay = 20;
	private int maxruns = 20;

	private Grid original = null;
	private Strategy strategy = Strategy.HUMAN;
	private boolean running = false;
	private int depth = 0;

	/** The singleton reference to the Logger instance. */
	private static Logger logger = Logger.getInstance();

	/** Get current class name, used for logging output. */
	private final String className = this.getClass().getSimpleName();

	public GridSolver(Grid grid, Strategy strategy, int delay, int depth) {

		logger.debug(className, "Initializing GridSolver...");

		running = false;
		succesfulMoves = 0;

		setGrid(grid);
		setDelay(delay);
		setMaxruns(maxruns);
		setStrategy(strategy);
		setDepth(depth);
	}

	// ///////////
	// setters
	// ///////////

	public void setGrid(Grid grid) {
		this.original = grid;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setMaxruns(int maxruns) {
		this.maxruns = maxruns;
	}

	private void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	private void setDepth(int depth) {
		this.depth = depth;
	}

	private int getDepth() {
		return this.depth;
	}

	// /////////////
	// start/stop
	// /////////////

	/** Benchmark start. */
	public void start() {

		if (!isRunning()) {
			logger.info(className, "Starting solver...");
			
			if (timer == null) {
				timer = new Timer();
			
				timer.schedule(this, 0, delay);
			}
			
			running = true;
		}
	}

	/** Stop benchmark. */
	public void stop() {

		if (isRunning()) {
			timer.cancel();
			timer.purge();

			timer = null;
			running = false;

			logger.info(className, "Solver stopped.");
		}
	}

	public boolean isRunning() {
		return running;
	}

	public static void makeMove(Grid grid, Direction direction) {

		if (direction == null) {
		} else if (grid.move(direction) != -1) {
			succesfulMoves += 1;
		}
	}

	public static int getSuccesfulMoves() {
		return succesfulMoves;
	}

	@Override
	public void run() {

		// keep playing until we run out of moves
		if (original.getPossibleMoves() == 0) {
			logger.info(className, "Solver cannot make any more moves.");
			stop();
		} else {

			Direction direction = null;

			if (strategy == Strategy.HUMAN) {
				direction = HumanSolver.selectMove(original, getDepth());
			}

			logger.debug(className, "Direction selected: " + direction);

			// make the selected move
			makeMove(original, direction);
		}
	}

	/** Setter for testing purposes only */
	public void setLogger(Logger logger) {
		GridSolver.logger = logger;
	}
}