package nl.tudelft.ti2206.buttons;

import com.badlogic.gdx.math.Rectangle;

/**
 * The Button class represents a basic button, from which other buttons
 * can be derived.
 */
public class Button {
	/** The coordinates and dimension. */
	private float x, y, width, height;

	/** The boundaries of the button. */
	private Rectangle bounds;

	/** Determines whether the button has been pressed or not. */
	protected boolean isPressed = false;

	/**
	 * Creates a new Button object with specified parameters.
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
	public Button(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		bounds = new Rectangle(x, y, width, height);
	}

	/**
	 * Determines if the button is clicked.
	 * 
	 * @param screenX
	 *            The x-coordinate of the click.
	 * @param screenY
	 *            The y-coordinate of the click.
	 * @return True if the button is clicked, false otherwise.
	 */
	private boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}

	/**
	 * @return The button's x-coordinate.
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return The button's y-coordinate.
	 */
	public float getY() {
		return y;
	}

	/**
	 * @return The button's height.
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @return The button's width.
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Checks if the button is touched or clicked.
	 * 
	 * @param screenX
	 *            The x-coordinate of the click.
	 * @param screenY
	 *            The y-coordinate of the click.
	 * @return True if the button is clicked, false otherwise.
	 */
	public boolean isTouchDown(int screenX, int screenY) {
		if (isClicked(screenX, screenY)) {
			isPressed = true;
			return true;
		}
		return false;
	}

	/**
	 * Checks if the button is released. This is only used to reset the
	 * isPressed instance variable.
	 * 
	 * @param screenX
	 *            The x-coordinate of the click.
	 * @param screenY
	 *            The y-coordinate of the click.
	 * @return True if the button is released, false otherwise.
	 */
	public boolean isTouchUp(int screenX, int screenY) {
		if (isClicked(screenX, screenY)) {
			isPressed = false;
			return true;
		}
		return false;
	}
	
	/**
	 * Sets the x coordinate of the button.
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}
}