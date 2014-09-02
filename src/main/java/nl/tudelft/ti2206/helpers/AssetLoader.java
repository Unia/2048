package nl.tudelft.ti2206.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AssetLoader {

	public static BitmapFont font;
	public static Sprite grid, t2, t4, t8, t16, t32, t64, t128, t256, t512,
			t1024, t2048, empty;
	private static Texture gridTex, t2Tex, t4Tex, t8Tex, t16Tex, t32Tex,
			t64Tex, t128Tex, t256Tex, t512Tex, t1024Tex, t2048Tex, emptyTex;

	public static void load() {
		gridTex = new Texture(
				Gdx.files.internal("src/main/resources/images/grid.png"));
		grid = new Sprite(gridTex);

		t2Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile2.png"));
		t4Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile4.png"));
		t8Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile8.png"));
		t16Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile16.png"));
		t32Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile32.png"));
		t64Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile64.png"));
		t128Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile128.png"));
		t256Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile256.png"));
		t512Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile512.png"));
		t1024Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile1024.png"));
		t2048Tex = new Texture(
				Gdx.files.internal("src/main/resources/images/tile2048.png"));
		emptyTex = new Texture(
				Gdx.files.internal("src/main/resources/images/empty.png"));

		t2 = new Sprite(t2Tex);
		t4 = new Sprite(t4Tex);
		t8 = new Sprite(t8Tex);
		t16 = new Sprite(t16Tex);
		t32 = new Sprite(t32Tex);
		t64 = new Sprite(t64Tex);
		t128 = new Sprite(t128Tex);
		t256 = new Sprite(t256Tex);
		t512 = new Sprite(t512Tex);
		t1024 = new Sprite(t1024Tex);
		t2048 = new Sprite(t2048Tex);
		empty = new Sprite(emptyTex);

		font = new BitmapFont(
				Gdx.files.internal("src/main/resources/fonts/tahoma.fnt"));
		font.setScale(.5f, -.5f);
	}

	public static Sprite getTile(int val) {
		switch (val) {
		case 2:
			return t2;
		case 4:
			return t4;
		case 8:
			return t8;
		case 16:
			return t16;
		case 32:
			return t32;
		case 64:
			return t64;
		case 128:
			return t128;
		case 256:
			return t256;
		case 512:
			return t512;
		case 1024:
			return t1024;
		case 2048:
			return t2048;
		default:
			return empty;
		}
	}

	public static void dispose() {
		gridTex.dispose();
		t2Tex.dispose();
		t4Tex.dispose();
		t8Tex.dispose();
		t16Tex.dispose();
		t32Tex.dispose();
		t64Tex.dispose();
		t128Tex.dispose();
		t256Tex.dispose();
		t512Tex.dispose();
		t1024Tex.dispose();
		t2048Tex.dispose();
		emptyTex.dispose();

		font.dispose();
	}
}
