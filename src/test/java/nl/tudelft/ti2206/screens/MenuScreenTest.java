package nl.tudelft.ti2206.screens;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class MenuScreenTest {

	@Mock
	private Skin skin;
	@Mock
	private BitmapFont font;
	@Mock
	private Stage stage;
	@Mock
	private Table table;
	@Mock
	private Cell<Label> labelCell;
	@Mock
	private Cell<TextButton> buttonCell;
	@Mock
	private Label label;
	@Mock
	private TextField field;
	@Mock
	private TextButton button;
	@Mock
	private GL20 gl;
	@Mock
	private Input input;

	private MenuScreen screen;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		screen = new MenuScreen(stage, table, label, button);
		Gdx.gl = gl;
		Gdx.input = input;
		doNothing().when(input).setInputProcessor(stage);
		doNothing().when(Gdx.gl).glClearColor(anyInt(), anyInt(), anyInt(),
				anyInt());
		doNothing().when(Gdx.gl).glClear(anyInt());

		when(labelCell.padTop(anyInt())).thenReturn(labelCell);
		when(labelCell.padBottom(anyInt())).thenReturn(labelCell);
		when(labelCell.row()).thenReturn(labelCell);

		when(buttonCell.padTop(anyInt())).thenReturn(buttonCell);
		when(buttonCell.padBottom(anyInt())).thenReturn(buttonCell);
		when(buttonCell.row()).thenReturn(buttonCell);

		when(table.getCell(label)).thenReturn(labelCell);
		when(table.getCell(button)).thenReturn(buttonCell);
	}

	@Test
	public void testDispose() {
		screen.dispose();
		verify(stage).dispose();
	}

	@Test
	public void testCreate() {
		screen.create();
		verify(input).setInputProcessor(stage);

		verify(table).add(label);
		verify(labelCell).padBottom(anyInt());
		verify(labelCell).row();

		verify(table, times(3)).add(button);
		verify(buttonCell, times(3)).padBottom(anyInt());
		verify(buttonCell, times(3)).row();

		verify(table).setFillParent(true);

		verify(stage).addActor(table);

		verify(button, times(3)).addListener(any(EventListener.class));
	}
}
