package nl.tudelft.ti2206.graphics.screens.overlays;

import nl.tudelft.ti2206.graphics.buttons.MenuButton;
import nl.tudelft.ti2206.graphics.screens.Screen;
import nl.tudelft.ti2206.utils.handlers.AssetHandler;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * The MultiWinScreen is displayed when the local player has won.
 */
public class MultiWinScreen {
	/** The background image. */
	private Image image;

	/** The button to go back to the menu. */
	private MenuButton menuButton;

	/** The stage of the parent screen. */
	private Stage stage;

	/** Constructs a new MultiWinScreen. */
	public MultiWinScreen(Screen parent) {
		stage = parent.getStage();
		image = new Image(AssetHandler.getInstance().getSkin(),
				"multiwonoverlay");
		menuButton = new MenuButton();
		addActors();

	}

	/** Constructor used for mock insertion */
	public MultiWinScreen(Stage stage, Image image, MenuButton menuButton) {
		this.stage = stage;
		this.image = image;
		this.menuButton = menuButton;
		addActors();
	}

	/** Adds all required actors to stage. */ 
	private void addActors() {
		stage.addActor(image);
		stage.addActor(menuButton);
	}
}
