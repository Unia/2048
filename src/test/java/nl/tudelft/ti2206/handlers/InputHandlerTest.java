package nl.tudelft.ti2206.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.tudelft.ti2206.game.GameWorld;
import nl.tudelft.ti2206.gameobjects.DrawableGrid;
import nl.tudelft.ti2206.gameobjects.Grid.Direction;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Input.Keys;

/**
 * Test class for the InputHandler.
 */
public class InputHandlerTest {
	/** The object under test. */
	private static InputHandler handler;

	/** A mock to verify behavior. */
	private static GameWorld world;

	/** A mock to verify behavior. */
	private static DrawableGrid grid;

	/**
	 * Initialize all mocks and creates a new InputHandler.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		world = Mockito.mock(GameWorld.class);
		grid = Mockito.mock(DrawableGrid.class);
		when(world.getGrid()).thenReturn(grid);

		handler = new InputHandler(world);
	}

	/**
	 * Tests if the constructor successfully creates a new SimpleButton object.
	 */
	@Test
	public void testConstructor() {
		assertNotNull(handler);
	}

	/**
	 * Make sure the move method is called with the correct parameter when the
	 * down arrow key is pressed.
	 */
	@Test
	public void testKeyDownDown() {
		handler.keyDown(Keys.DPAD_DOWN);
		verify(grid).move(Direction.DOWN);
	}

	/**
	 * Make sure the move method is called with the correct parameter when the
	 * up arrow key is pressed.
	 */
	@Test
	public void testKeyDownUp() {
		handler.keyDown(Keys.DPAD_UP);
		verify(grid).move(Direction.UP);
	}

	/**
	 * Make sure the move method is called with the correct parameter when the
	 * left arrow key is pressed.
	 */
	@Test
	public void testKeyDownLeft() {
		handler.keyDown(Keys.DPAD_LEFT);
		verify(grid).move(Direction.LEFT);
	}

	/**
	 * Make sure the move method is called with the correct parameter when the
	 * right arrow key is pressed.
	 */
	@Test
	public void testKeyDownRight() {
		handler.keyDown(Keys.DPAD_RIGHT);
		verify(grid).move(Direction.RIGHT);
	}

	/**
	 * Make sure the keyDown method returns false when the key is invalid.
	 */
	@Test
	public void testKeyDownInvalid() {
		assertEquals(false, handler.keyDown(Keys.BACKSLASH));
	}

//	@Test
//	public void testTouchDown() {
//		handler.touchDown(0, 0, 0, Buttons.LEFT);
//	}
}
