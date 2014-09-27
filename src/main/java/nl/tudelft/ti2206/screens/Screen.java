package nl.tudelft.ti2206.screens;

import nl.tudelft.ti2206.log.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

/**
 * An abstract class for screens.
 * 
 * Code based on:
 * http://gamedev.stackexchange.com/questions/75902/how-to-design-
 * transparent-screen-in-libgdx
 */
public abstract class Screen implements Disposable {
	/** The singleton reference to the Logger instance. */
	private static Logger logger = Logger.getInstance();
	
	/** Get current class name, used for logging output. */
	private final String className = this.getClass().getSimpleName();

	/** The scene graph. */
	protected Stage stage;
	
	protected DrawBehavior drawbehavior;

	/**
	 * Called when the screen is shown. Used for initialization.
	 */
	public void create() {
		logger.debug(className, "Creating window...");
		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * Draws the screen.
	 */
	public void draw() {
		drawbehavior.draw();
	}
	
	public void setDrawBehavior(DrawBehavior newDrawBehavior){
		drawbehavior = newDrawBehavior;
	}
	
	/**
	 * Determines if the screen is an overlay or not. Overlays will not cause
	 * screens below it to automatically exit.
	 * 
	 * @return True if the screen is an overlay, false otherwise.
	 */
	public boolean isOverlay() {
		return false;
	}

	/**
	 * Pauses the screen.
	 */
	public void pause() {
	}

	/**
	 * Called when the screen is resized.
	 * 
	 * @param width
	 *            The new game window width (in pixels).
	 * @param height
	 *            The new game window height (in pixels).
	 */
	public void resize(int width, int height) {
	}

	/**
	 * Resumes the screen after a pause.
	 */
	public void resume() {
		Gdx.input.setInputProcessor(stage);
	}

	/**
	 * Updates the screen.
	 */
	public void update() {
		stage.act();
	}

	@Override
	public void dispose() {
		logger.debug(className, "Closing window...");
		stage.dispose();
	}

	/**
	 * @return The stage.
	 */
	public Stage getStage() {
		return stage;
	}
}