package com.shapeshifter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.shapeshifter.Actor.Actor;

public class GameScreen extends ScreenAdapter {

    private GameWorld gameworld;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Box2DDebugRenderer box2DDebugRenderer;

    //shape sprites
    private Texture square = new Texture("square.png");
    private Texture triangle = new Texture("triangle.png");

    public GameScreen(OrthographicCamera camera, GameWorld gameworld) {
        this.gameworld = gameworld;
        this.camera = camera;
        camera.zoom = 2;
        this.batch = new SpriteBatch();

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());    //adjust camera if window is resized, but also sets camera position to 0,0
    }

    public void update() {
        gameworld.gameLoop();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
        if (Gdx.input.isKeyPressed(Input.Keys.E)) camera.zoom += 0.1;
        if (Gdx.input.isKeyPressed(Input.Keys.Q) && camera.zoom > 1) camera.zoom -= 0.1;

        camera.position.x = gameworld.player.getPosX();
        camera.position.y = gameworld.player.getPosY();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

    }

    @Override
    public void render(float delta) {
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(gameworld.background, 0, 0, 1920*5, 1080*5);
        for (Actor i : gameworld.actors) {
            batch.draw(i.getTexture(),
                    i.getPosX(), i.getPosY(),
                    i.getTexture().getWidth()/2, i.getTexture().getHeight()/2,
                    i.getTexture().getWidth(), i.getTexture().getHeight(),
                    0.8f, 0.8f,
                    i.getRotation(),
                    0, 0,
                    i.getTexture().getWidth(), i.getTexture().getHeight(),
                    false, false);
        }
        batch.end();

        System.out.println(Gdx.graphics.getFramesPerSecond());
    }
}
