package com.shapeshifter.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.shapeshifter.Boot;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Shape Shifter";
		config.vSyncEnabled = true;
		config.width = 1000;
		config.height = 1000;
		config.resizable = true;

		new LwjglApplication(new Boot(), config);

	}
}
