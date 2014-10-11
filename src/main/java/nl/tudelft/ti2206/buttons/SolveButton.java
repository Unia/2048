package nl.tudelft.ti2206.buttons;

import nl.tudelft.ti2206.drawables.DrawableTile;
import nl.tudelft.ti2206.game.TwentyFourtyGame;
import nl.tudelft.ti2206.gameobjects.Grid;
import nl.tudelft.ti2206.handlers.AssetHandler;
import nl.tudelft.ti2206.solver.GridSolver;
import nl.tudelft.ti2206.solver.GridSolver.Strategy;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * A button to call a solver to solve the game.
 */
public class SolveButton extends TextButton {
	private GridSolver gridSolver;

	/** Constructs a new SolveButton. */
	public SolveButton(final Grid grid) {
		super("Solve", AssetHandler.getInstance().getSkin(), "small");
		this.setHeight(50);
		this.setWidth(DrawableTile.TILE_WIDTH);
		this.setX(DrawableTile.TILE_X - DrawableTile.TILE_WIDTH / 2 - TwentyFourtyGame.GAP / 2);
		this.setY(100 / 2 - this.getHeight() / 2);

		gridSolver = new GridSolver(grid, Strategy.HUMAN, 750, 6);

		this.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (gridSolver == null) {
					gridSolver = new GridSolver(grid, Strategy.HUMAN, 750, 6);
				}
				
				if (gridSolver.isRunning()) {
					gridSolver.stop();
					gridSolver = null;
				} else {
					gridSolver.start();
				}
			}
		});
	}
}
