package nl.tudelft.ti2206.screens;

import nl.tudelft.ti2206.buttons.MenuButton;
import nl.tudelft.ti2206.handlers.AssetHandler;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * The ConnectionLostScreen is displayed when the connection drops.
 */
public class ConnectionLostScreen extends Screen {
	/** The transparent background image. */
	private Image image;

	/** The button to go back to the menu. */
	private MenuButton menuButton;

	/** Constructs a new ConnectionLostScreen. */
	public ConnectionLostScreen() {
		stage = new Stage();
		image = new Image(AssetHandler.getSkin(), "connectionlostoverlay");
		menuButton = new MenuButton();
	}

	/** Constructor used for mock insertion */
	public ConnectionLostScreen(Stage stage, Image image, MenuButton menuButton) {
		this.stage = stage;
		this.image = image;
		this.menuButton = menuButton;
	}

	@Override
	public void create() {
		super.create();
		stage.addActor(image);
		stage.addActor(menuButton);
	}

	@Override
	public void draw() {
		stage.draw();
	}

	@Override
	public boolean isOverlay() {
		return true;
	}
}
