package old.game;

import old.handlers.AssetHandler;
import old.handlers.ButtonHandler;
import old.handlers.PreferenceHandler;

import com.badlogic.gdx.Game;

/**
 * The TwentyFourtyGame is used to hook into LibGDX. It extends Game, which
 * implements LibGDX's ApplicationListener interface.
 * 
 * Through implementing this interface, we receive events from LibGDX. These
 * events are received in the Screen, for which we have written our own
 * GameScreen. In essence, GameScreen is our 'controller' class, because
 * TwentyFourtyGame delegates all events to it.
 * 
 * @author group-21
 */
public class TwentyFourtyGame extends Game {
	@Override
	public void create() {
		AssetHandler.load();
		ButtonHandler.load();
		PreferenceHandler.initScores();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		getScreen().dispose();
		AssetHandler.dispose();
	}
}
