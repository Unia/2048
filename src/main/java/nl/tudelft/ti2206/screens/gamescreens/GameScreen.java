package nl.tudelft.ti2206.screens.gamescreens;

import nl.tudelft.ti2206.buttons.RedoButton;
import nl.tudelft.ti2206.buttons.RestartButton;
import nl.tudelft.ti2206.buttons.SolveButton;
import nl.tudelft.ti2206.buttons.UndoButton;
import nl.tudelft.ti2206.drawables.DrawableGrid;
import nl.tudelft.ti2206.drawables.Scores;
import nl.tudelft.ti2206.game.TwentyFourtyGame;
import nl.tudelft.ti2206.game.TwentyFourtyGame.GameState;
import nl.tudelft.ti2206.gameobjects.Grid;
import nl.tudelft.ti2206.handlers.InputHandler;
import nl.tudelft.ti2206.handlers.ProgressHandler;
import nl.tudelft.ti2206.handlers.ScreenHandler;
import nl.tudelft.ti2206.screens.drawbehaviour.DrawBeige;
import nl.tudelft.ti2206.screens.Screen;
import nl.tudelft.ti2206.screens.overlays.LoseScreen;
import nl.tudelft.ti2206.screens.overlays.WinScreen;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * The GameScreen is the screen for a singleplayer game.
 */
public class GameScreen extends Screen {
	/** The grid holding all the Tiles. */
	private Grid grid;

	/** The score tiles above the Grid. */
	private Scores scores;

	/** The grid that is actually drawn. */
	private DrawableGrid drawableGrid;

	/** The button to undo the last move. */
	private UndoButton undoButton;

	/** The button to redo the last move. */
	private RedoButton redoButton;

	/** The button to restart the current game. */
	private RestartButton restartButton;

	/** The button to let a solver solve the game. */
	private SolveButton solveButton;

	/** The singleton reference to the ProgressHandler class. */
	private ProgressHandler progressHandler = ProgressHandler.getInstance();

	/** The singleton reference to the ScreenHandler class. */
	private static ScreenHandler screenHandler = ScreenHandler.getInstance();

	/** Constructs a new GameScreen. */
	public GameScreen() {
		stage = new Stage();
		grid = progressHandler.loadGame();
		drawableGrid = new DrawableGrid(grid.getTiles());
		restartButton = new RestartButton(grid, false);
		solveButton = new SolveButton(grid);
		undoButton = new UndoButton(grid);
		redoButton = new RedoButton(grid);
		scores = new Scores();

		grid.addObserver(scores);
		this.setDrawBehavior(new DrawBeige(stage));
	}

	/** Constructor to insert Mock objects. For testing only. */
	public GameScreen(Stage stage, Grid grid, RestartButton restartButton,
			SolveButton solveButton, UndoButton undoButton,
			RedoButton redoButton, Scores scores) {
		this.stage = stage;
		this.grid = grid;
		this.restartButton = restartButton;
		this.solveButton = solveButton;
		this.undoButton = undoButton;
		this.redoButton = redoButton;
		this.scores = scores;
		grid.addObserver(scores);
		this.setDrawBehavior(new DrawBeige(stage));
	}

	@Override
	public void create() {
		super.create();
		stage.addListener(new InputHandler(grid));

		/* Create the main group and pack everything in it. */
		stage.addActor(drawableGrid);
		stage.addActor(restartButton);
		stage.addActor(solveButton);
		stage.addActor(scores);		
		stage.addActor(undoButton);
		stage.addActor(redoButton);
	}

	@Override
	public void update() {
		super.update();
 
		if (grid.getCurrentHighestTile() == 11
				&& !TwentyFourtyGame.isContinuing()) {
			TwentyFourtyGame.setState(GameState.WON);
			screenHandler.add(new WinScreen(grid));
		} else if (grid.getPossibleMoves() == 0) {
			TwentyFourtyGame.setState(GameState.LOST);
			screenHandler.add(new LoseScreen(grid));
		}
	}

	@Override
	public void dispose() {
		progressHandler.saveGame(grid);
		super.dispose();
	}
}