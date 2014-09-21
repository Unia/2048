package nl.tudelft.ti2206.screens;

import nl.tudelft.ti2206.buttons.MenuButton;
import nl.tudelft.ti2206.handlers.AssetHandler;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * The MultiWinScreen is displayed when the local player has won.
 */
public class MultiWinScreen extends Screen {
	/** The transparent background image. */
	private Image image;

	/** The button to go back to the menu. */
	private MenuButton menuButton;

	/** Constructs a new MultiWinScreen. */
	public MultiWinScreen() {
		stage = new Stage();
		image = new Image(AssetHandler.getSkin(), "wonoverlay");
		//image = new Image(AssetHandler.getSkin(), "multiwonoverlay");
		menuButton = new MenuButton();
	}

	/** Constructor used for mock insertion */
	public MultiWinScreen(Stage stage, Image image, MenuButton cancelButton) {
		this.stage = stage;
		this.image = image;
		this.menuButton = cancelButton;
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