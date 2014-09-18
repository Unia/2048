package nl.tudelft.ti2206.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;

import nl.tudelft.ti2206.game.GameWorld;
import nl.tudelft.ti2206.handlers.AssetHandler;

/**
 * The DrawableGrid class is a subclass of Grid, that knows how to draw itself.
 */
public class DrawableGrid extends Grid implements Drawable {
	/** The width of the Grid. */
	private static final int GRID_WIDTH = 400;

	/** The height of the Grid. */
	private static final int GRID_HEIGHT = 400;

	/** The base Grid x-coordinate. */
	private static final int GRID_X = 100;

	/** The base Grid y-coordinate. */
	private static final int GRID_Y = 100;

	/**
	 * Constructs a new DrawableGrid instance, by calling super with its
	 * supplied parameters.
	 * 
	 * @param world
	 *            The GameWorld this Grid will be placed in.
	 * @param isEmpty
	 *            True if the grid should be empty.
	 */
	public DrawableGrid(GameWorld world, boolean isEmpty) {
		super(world, isEmpty);
	}

	/**
	 * Sets a Tile's parameters by index and starts the spawning animation.
	 * 
	 * @param index
	 *            The Tile's index on the grid.
	 * @param value
	 *            The Tile's value (should be a multiple of 2 or 0).
	 * @param isMerged
	 *            True if the Tile is merged.
	 */
	@Override
	public void setTile(int index, int value, boolean isMerged) {
		super.setTile(index, value, isMerged);
		grid[index].spawn();
	}

	@Override
	public void update() {
		for (DrawableTile t : grid) {
			t.update();
		}
		updateHighestTile();
	}

	@Override
	public void draw(Batch batch) {
		batch.draw(AssetHandler.grid, GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
	}
}
