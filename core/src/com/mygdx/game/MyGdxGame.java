package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Player p1;
	char[][] m = new char[45][6];
	String mapName = "maps/testMap.txt";

	@Override
	public void create () {
		batch = new SpriteBatch();
		p1 = new Player();
		map.mapRead(mapName, m);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.mapDraw(batch, m);
		batch.draw(p1.img, p1.x, p1.y);
		batch.end();
	}
}
