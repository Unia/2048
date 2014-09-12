package nl.tudelft.ti2206.helpers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import nl.tudelft.ti2206.game.HeadlessLauncher;

import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AssetHandlerTest {

	private static AssetManager manager;
	private static Texture texture;
	private static BitmapFont font;

	/**
	 * Sets up the test environment. Mockito is used extensively to prevent GL
	 * related classes and methods from throwing NullPointerExceptions when
	 * running the HeadlessLauncher.
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new HeadlessLauncher().launch();

		manager = mock(AssetManager.class);
		AssetHandler.setAssetManager(manager);

		texture = mock(Texture.class);
		font = mock(BitmapFont.class);

		stub(manager.get(anyString(), eq(Texture.class))).toReturn(texture);
		stub(manager.get(anyString(), eq(BitmapFont.class))).toReturn(font);

	}

	@Test
	public void testIsLibraryInitialized() {
		assertFalse(AssetHandler.isLibraryInitialized());
	}

	/**
	 * Tests whether the LibGDX methods of the AssetManager are called every
	 * time a Texture or Font is loaded. In other words, it makes sure that all
	 * Textures and BitmapFonts needed for the application are loaded.
	 */
	@Test
	public void testLoad() {
		AssetHandler.load();
		verify(manager, times(20)).load(anyString(), eq(Texture.class));
		verify(manager, times(2)).load(anyString(), eq(BitmapFont.class));
	}

	/**
	 * Tests if the getTile method of AssetHandler returns a Sprite object for
	 * every possible tile value.
	 */
	@Test
	public void testGetTile() {
		assertTrue(AssetHandler.getTile(0) instanceof Sprite);

		for (int i = 2; i < 10000; i *= 2) {
			assertTrue(AssetHandler.getTile(i) instanceof Sprite);
		}
	}

	/**
	 * Tests if the manager is disposed.
	 */
	@Test
	public void testDispose() {
		AssetHandler.dispose();
		verify(manager).dispose();
	}
}