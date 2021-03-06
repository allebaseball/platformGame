package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Tools.MyInputProcessor;

public class Team3 extends Game {
	public static final int V_WIDTH = 416 * 16 / 9;
	public static final int V_HEIGHT = 416;
	public static final float PPM = 100;

	public SpriteBatch batch;
	public SpriteBatch FPSbatch;

	private PlayScreen playS;

	@Override
	public void create () {
		batch = new SpriteBatch();
		FPSbatch = new SpriteBatch();

		playS = new PlayScreen(this);
		setScreen(playS);

		MyInputProcessor inputProcessor = new MyInputProcessor(playS);
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render () {
		super.render();
	}

}