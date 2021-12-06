package com.shapeshifter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends ScreenAdapter {

    private GameWorld gameworld;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;                            //ARE WE GONNA USE THIS?
    private Box2DDebugRenderer box2DDebugRenderer;

    //shape sprites
    private Texture square = new Texture("square.png");
    private Texture triangle = new Texture("triangle.png");

    public GameScreen(OrthographicCamera camera, GameWorld gameworld) {
        this.gameworld = gameworld;
        this.camera = camera;
        this.camera.position.set(new Vector3(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight(), 0));
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0, 0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
    }

    public void update() {
        world.step(1/60f, 6, 2); //what do iteration variables do? Tutorial said to set as these with no explanation. Is this even being used right now?
        gameworld.gameLoop();

        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());    //adjust camera if window is resized
        camera.update(); //?
        batch.setProjectionMatrix(camera.combined);

    }


    @Override
    public void render(float delta) {
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (Actor i : gameworld.actors) {
            batch.draw(i.getTexture(),
                    i.getPosX()/100, i.getPosY()/100,
                    i.getTexture().getWidth()/2, i.getTexture().getHeight()/2,
                    i.getTexture().getWidth(), i.getTexture().getHeight(),
                    0.8f, 0.8f,
                    i.getRotation(),
                    0, 0,
                    i.getTexture().getWidth(), i.getTexture().getHeight(),
                    false, false);
        }
        batch.end();
    }
}
