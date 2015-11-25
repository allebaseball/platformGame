package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Team3;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Team3";
		config.width = 1280/2;
		config.height = 720/2;
		config.fullscreen = false;
		config.vSyncEnabled = false;
		new LwjglApplication(new Team3(), config);
	}
}
