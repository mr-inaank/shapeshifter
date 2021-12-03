package com.shapeshifter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.Game;

public class Boot extends Game {

	public static Boot INSTANCE;
	private int screenWidth, screenHeight;
	private OrthographicCamera camera;


	public Boot() {
		INSTANCE = this;
		//this.camera.position.set(new Vector3(screenWidth/2, screenHeight/2, 0));
		//this.batch = new SpriteBatch();
		//this.world = new World(new Vector2(0, 0 ), false);
		//this.box2DDebugRenderer = new Box2DDebugRenderer();
	}
	
	@Override
	public void create () {
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, screenWidth, screenHeight);
		setScreen(new GameScreen(camera));

	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}
}
