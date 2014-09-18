package nl.tudelft.ti2206.buttons;

import nl.tudelft.ti2206.game.GameWorld;

/**
 * The GameButton class represents a basic button to be used in-game.
 * It defines an abstract onClick method, which derived buttons must
 * implement to define their own behavior.
 */
public abstract class GameButton extends Button {
	/**
	 * Creates a new GameButton object with specified parameters.
	 * 
	 * @param x
	 *            The x-coordinate for the button.
	 * @param y
	 *            The y-coordinate for the button.
	 * @param width
	 *            The width for the button.
	 * @param height
	 *            The height for the button.
	 */
	public GameButton(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	/**
	 * Each derived button should override this method to ensure specific
	 * behavior when it is clicked. The GameWorld parameter is provided to allow
	 * access to the GameWorld object.
	 * 
	 * @param world
	 *            A reference to the current GameWorld.
	 */
	public abstract void onClick(GameWorld world);
}
