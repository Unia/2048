package nl.tudelft.ti2206.handlers;

import nl.tudelft.ti2206.buttons.ContinueButton;
import nl.tudelft.ti2206.buttons.RestartButton;
import nl.tudelft.ti2206.game.GameWorld;

/**
 * The ButtonHandler is responsible for managing the buttons.
 */
public class ButtonHandler {
	/** The restart button. */
	private static RestartButton restartButton;

	/** The continue button. */
	private static ContinueButton continueButton;

	/**
	 * Initializes all buttons.
	 */
	public static void load() {
		restartButton = new RestartButton(AssetHandler.newgame.getX(),
				AssetHandler.newgame.getY(), AssetHandler.newgame.getWidth(),
				AssetHandler.newgame.getHeight(), AssetHandler.newgame,
				AssetHandler.newgame);

		continueButton = new ContinueButton(AssetHandler.continuebutton.getX(),
				AssetHandler.continuebutton.getY(), AssetHandler.continuebutton.getWidth(),
				AssetHandler.continuebutton.getHeight(), AssetHandler.continuebutton,
				AssetHandler.continuebutton);
	}

	/**
	 * Delegates a touchDown event from the InputHandler to the correct button.
	 * 
	 * @param world A reference to the current GameWorld, so the buttons can interact with it.
	 * @param screenX The x-coordinate of the touchDown event.
	 * @param screenY The y-coordinate of the touchDown event.
	 * @return True if the touchDown event is processed, false otherwise. 
	 */
	public static boolean touchDown(GameWorld world, int screenX, int screenY) {
		if (restartButton.isTouchDown(screenX, screenY)) {
			restartButton.onClick(world);
			return true;
		}

		if (continueButton.isTouchDown(screenX, screenY)) {
			continueButton.onClick(world);
			return true;
		}

		return false;
	}

	/**
	 * @return The RestartButton object.
	 */
	public static RestartButton getRestartButton() {
		return restartButton;
	}

	/**
	 * @return The ContinueButton object.
	 */
	public static ContinueButton getContinueButton() {
		return continueButton;
	}
}
