package nl.tudelft.ti2206.screens;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.tudelft.ti2206.buttons.CancelButton;
import nl.tudelft.ti2206.buttons.PlayButton;
import nl.tudelft.ti2206.handlers.AssetHandler;
import nl.tudelft.ti2206.net.Networking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class HostScreenTest {

	@Mock
	private Skin skin;
	@Mock
	private BitmapFont font;
	@Mock
	private Stage stage;
	@Mock
	private Table table;
	@Mock
	private Cell<Label> cell;
	@Mock
	private Label label;
	@Mock
	private TextField field;
	@Mock
	private PlayButton playButton;
	@Mock
	private CancelButton cancelButton;
	@Mock
	private GL20 gl;
	@Mock
	private Input input;

	private HostScreen screen;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Skin skin = mock(Skin.class);
		AssetHandler.setSkin(skin);
		
		screen = new HostScreen(stage, table, label, playButton, cancelButton);
		Gdx.gl = gl;
		Gdx.input = input;
		doNothing().when(input).setInputProcessor(stage);
		doNothing().when(Gdx.gl).glClearColor(anyInt(), anyInt(), anyInt(),
				anyInt());
		doNothing().when(Gdx.gl).glClear(anyInt());
		
		when(cell.padTop(anyInt())).thenReturn(cell);
		when(cell.padBottom(anyInt())).thenReturn(cell);
		when(cell.row()).thenReturn(cell);
		
		when(table.getCell(label)).thenReturn(cell);
	}

	@Test
	public void testDispose() {
		screen.dispose();
		verify(stage).dispose();
	}

	@Test
	public void testCreate() {
		screen.create();

		verify(table, times(3)).add(label);
		verify(cell, times(3)).padTop(anyInt());
		verify(cell, times(3)).padBottom(anyInt());
		verify(cell, times(3)).row();

		verify(stage).addActor(table); 
		verify(stage).addActor(cancelButton);
		verify(stage).addActor(playButton);
		
		verify(playButton).addListener(any(EventListener.class));

		verify(input).setInputProcessor(stage);
	}

	@Test
	public void testDraw() {
		screen.draw();
		verify(gl).glClearColor(anyInt(), anyInt(), anyInt(), anyInt());
		verify(gl).glClear(anyInt());
		
		verify(stage).draw();
	}

	@Test
	public void testUpdate() {
		screen.update();
		verify(stage).act();
	}
}