package com.shapeshifter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Boot extends Game {

	public static Boot INSTANCE;
	private OrthographicCamera camera;
	private GameWorld gameworld;
	private Sound music;


	public Boot() {
		INSTANCE = this;
	}
	
	@Override
	public void create () {
		music = Gdx.audio.newSound(Gdx.files.internal("track.ogg"));
//		music.loop();
//		this.gameworld = new GameWorld();
		this.gameworld = GameWorld.INSTANCE;
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		setScreen(new GameScreen(camera, gameworld));

	}
}
