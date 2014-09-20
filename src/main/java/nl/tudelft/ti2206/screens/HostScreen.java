package nl.tudelft.ti2206.screens;

import nl.tudelft.ti2206.buttons.CancelButton;
import nl.tudelft.ti2206.buttons.PlayButton;
import nl.tudelft.ti2206.handlers.AssetHandler;
import nl.tudelft.ti2206.net.Networking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class HostScreen implements Screen {
	private Stage stage;
	private Table table;
	private Label label;
	private CancelButton cancel;
	private PlayButton play;

	public HostScreen() {
		stage = new Stage();
		table = new Table();
		label = new Label("Your opponent's destiny\r\nlies beyond one of these:\r\n", AssetHandler.getSkin());
		cancel = new CancelButton();
		play = new PlayButton();
	}

	/** Constructor for injecting mock objects. For testing purposes only. */
	public HostScreen(Stage stage, Table table, Label label, PlayButton play, CancelButton cancel) {
		this.stage = stage;
		this.table = new Table();
		this.label = label;
		this.cancel = cancel;
		this.play = play;
	}

	@Override
	public void create() {
		table.add(label).padTop(20).padBottom(20).row();

		String addrList = Networking.strAddresses();
		Label addresses = new Label(addrList, AssetHandler.getSkin());
		
		table.add(label).padTop(20).padBottom(5).row();
		table.add(addresses).padTop(5).padBottom(20).row();

		table.setFillParent(true);
		stage.addActor(table);
		stage.addActor(cancel);
		stage.addActor(play);

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void draw() {
		/* Draw beige background in the screen. */
		Gdx.gl.glClearColor(.976f, .969f, .933f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	@Override
	public boolean isOverlay() {
		return false;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void update() {
		stage.act();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
