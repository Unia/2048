package nl.tudelft.ti2206.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * The AssetHandler is used to load and hold all the assets (textures and fonts)
 * the game requires.
 */
public class AssetHandler {
	/** The AssetManager is used to load and get all our textures and font. */
	private static AssetManager manager = new AssetManager();

	/** The Skin contains all our textures and fonts. */
	private static Skin skin = new Skin();

	/**
	 * Checks if the manager is done loading all the textures and font.
	 * 
	 * @return true If the manager is done, false otherwise.
	 */
	public static boolean isLibraryInitialized() {
		/* The check for Gdx.app is required to test on headless mode. */
		return Gdx.app != null && manager.update();
	}

	/**
	 * Instructs the AssetManager to load all the required assets (textures and
	 * fonts).
	 * 
	 * Blocks until the AssetManager is done.
	 */
	public static void load() {
		/*
		 * Queue all of these items for loading, order does not really matter I
		 * think, since we wait for everything to be done anyway.
		 */
		manager.load("src/main/resources/fonts/fonts.atlas", TextureAtlas.class);
		manager.load("src/main/resources/images/icons/icons.atlas",
				TextureAtlas.class);
		manager.load("src/main/resources/images/tiles/tiles.atlas",
				TextureAtlas.class);
		manager.load("src/main/resources/images/scoretiles/scoretiles.atlas",
				TextureAtlas.class);
		manager.load("src/main/resources/images/button.png", Texture.class);
		manager.load("src/main/resources/images/grid.png", Texture.class);
		manager.load("src/main/resources/images/lostoverlay.png", Texture.class);
		manager.load("src/main/resources/images/wonoverlay.png", Texture.class);
		manager.load("src/main/resources/images/multilostoverlay.png", Texture.class);
		manager.load("src/main/resources/images/multiwonoverlay.png", Texture.class);

		/*
		 * Instruct the asset manager to load everything in its queue, block
		 * until this is done.
		 */
		manager.finishLoading();

		/* Load all the textures into the Skin object. */
		setupSkin();
	}

	/**
	 * Uses the AssetManager to get all the textures and fonts into the Skin.
	 */
	private static void setupSkin() {
		TextureAtlas fonts = manager.get(
				"src/main/resources/fonts/fonts.atlas", TextureAtlas.class);
		TextureAtlas icons = manager.get(
				"src/main/resources/images/icons/icons.atlas",
				TextureAtlas.class);
		TextureAtlas tiles = manager.get(
				"src/main/resources/images/tiles/tiles.atlas",
				TextureAtlas.class);
		TextureAtlas scoretiles = manager.get(
				"src/main/resources/images/scoretiles/scoretiles.atlas",
				TextureAtlas.class);

		skin.addRegions(fonts);
		skin.addRegions(icons);
		skin.addRegions(tiles);
		skin.addRegions(scoretiles);
		skin.add("button", manager.get("src/main/resources/images/button.png", Texture.class));
		skin.add("grid", manager.get("src/main/resources/images/grid.png", Texture.class));
		skin.add("lostoverlay", manager.get(
				"src/main/resources/images/lostoverlay.png", Texture.class));
		skin.add("wonoverlay", manager.get(
				"src/main/resources/images/wonoverlay.png", Texture.class));
		skin.add("multilostoverlay", manager.get(
				"src/main/resources/images/multilostoverlay.png", Texture.class));
		skin.add("multiwonoverlay", manager.get(
				"src/main/resources/images/multiwonoverlay.png", Texture.class));
	}

	/**
	 * Returns the Skin object, so other classes can load their assets.
	 * 
	 * @return The Skin object.
	 */
	public static Skin getSkin() {
		return skin;
	}

	/**
	 * Sets the AssetManager to be used by the AssetHandler. Needed for the
	 * headless application which is used for DevHub. Since DevHub cannot make
	 * use of GL related classes, it is necessary to test the AssetHandler in a
	 * very specific way. See {@link AssetHandlerTest}.
	 * 
	 * @param assetManager
	 *            The AssetManager to be used by the AssetHandler.
	 */
	public static void setAssetManager(AssetManager assetManager) {
		manager = assetManager;
	}

	/**
	 * Sets the Skin to be used by the AssetHandler. Needed for the
	 * headless application which is used for DevHub. Since DevHub cannot make
	 * use of GL related classes, it is necessary to test the AssetHandler in a
	 * very specific way. See {@link AssetHandlerTest}.
	 * 
	 * @param newSkin
	 *            The Skin to be used by the AssetHandler.
	 */
	public static void setSkin(Skin newSkin) {
		skin = newSkin;
	}

	/**
	 * Makes the Skin load a file that contains all resources.
	 * Required for headless testing.
	 * 
	 * @param file The file containing the resources.
	 */
	public static void loadSkinFile(FileHandle file) {
		skin.load(file);
	}

	/**
	 * Disposes of all the textures, the font and the AssetManager.
	 */
	public static void dispose() {
		manager.dispose();
	}
}
