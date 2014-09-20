package nl.tudelft.ti2206.screens;

import java.util.Stack;

import com.badlogic.gdx.Gdx;

public class ScreenHandler {
	private static Stack<Screen> screenStack = new Stack<Screen>();

	/**
	 * Adds the specified screen to the service.
	 *
	 * @param screen
	 *            The screen.
	 */
	public static void add(Screen screen) {
		screen.create();
		screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		screenStack.push(screen);
	}

	/**
	 * Disposes cleanly of all the screens.
	 * */
	public static void dispose() {
		for (Screen screen : screenStack) {
			if (screen == null)
				continue;
			screen.dispose();
		}
		screenStack.clear();
	}

	/**
	 * Draws all screens in the service.
	 */
	public static void draw() {
		for (Screen screen : screenStack) {
			if (screen == null)
				continue;
			screen.draw();
		}
	}

	/**
	 * Pauses all screens in the service.
	 */
	public static void pause() {
		for (Screen screen : screenStack) {
			if (screen == null)
				continue;
			screen.pause();
		}
	}

	/**
	 * Called when the game window is resized.
	 *
	 * @param width
	 *            The new game window width (in pixels).
	 * @param height
	 *            The new game window height (in pixels).
	 */
	public static void resize(int width, int height) {
		for (Screen screen : screenStack) {
			if (screen == null)
				continue;
			screen.resize(width, height);
		}
	}

	/**
	 * Resumes all screens in the service.
	 */
	public static void resume() {
		for (Screen screen : screenStack) {
			if (screen == null)
				continue;
			screen.resume();
		}
	}

	/**
	 * Updates the screen service.
	 */
	public static void update() {
		boolean coveredByOtherScreen = false;
		for (int i = screenStack.size() - 1; i >= 0; i--) {
			Screen screen = screenStack.get(i);
			if (screen == null) {
				screenStack.remove(i);
				continue;
			}
			screen.update();
			if (coveredByOtherScreen)
				remove(screen);
			if (!screen.isOverlay())
				coveredByOtherScreen = true;
		}
	}

	/**
	 * Removes the specified screen from the service.
	 *
	 * @param screen
	 *            The screen.
	 */
	private static void remove(Screen screen) {
		screen.dispose();
		screenStack.remove(screen);
	}
}