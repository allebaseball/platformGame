package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.Team3;
import com.mygdx.game.Tools.B2WorldCreator;

public class PlayScreen implements Screen{
    private Team3 game;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    public Player p1;

    private BitmapFont FPSfont;

    public PlayScreen(Team3 game) {
        this.game = game;

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Team3.V_WIDTH / Team3.PPM, Team3.V_HEIGHT / Team3.PPM , gamecam);

        hud = new Hud(game.batch);

        FPSfont = new BitmapFont();

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/TestTiledMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Team3.PPM);

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        //Create player
        p1 = new Player(world);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
//        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
//            p1.b2body.setLinearVelocity(new Vector2(p1.b2body.getLinearVelocity().x, 0f));
//            p1.b2body.applyLinearImpulse(new Vector2(0, 5f), p1.b2body.getWorldCenter(), true);
//        }
//
//        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
//            p1.b2body.setLinearVelocity(2f, p1.b2body.getLinearVelocity().y);
//        }
//
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && p1.b2body.getLinearVelocity().x <= 2) {
//            p1.b2body.applyLinearImpulse(new Vector2(0.1f, 0), p1.b2body.getWorldCenter(), true);
//        }
//
//        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
//            p1.b2body.setLinearVelocity(-2f, p1.b2body.getLinearVelocity().y);
//        }
//
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && p1.b2body.getLinearVelocity().x >= -2) {
//            p1.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), p1.b2body.getWorldCenter(), true);
//        }
//
//        //Player change
//        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
//            p1.switchPlayer(-1);
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
//            p1.switchPlayer(1);
//        }
    }

    public void update(float dt) {
        handleInput(dt);
        p1.updateMotion(dt);

        world.step(1 / 60f, 6, 2);

        p1.update(dt);

        if (p1.b2body.getPosition().x > gamePort.getWorldWidth() / 2)
            gamecam.position.x = p1.b2body.getPosition().x;

//        gamecam.position.x = p1.b2body.getPosition().x;
//        gamecam.position.y = p1.b2body.getPosition().y;

//        System.out.println(p1.b2body.getPosition().x + p1.b2body.getPosition().y);

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        //Set color and clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Render game map
        renderer.render();

        //Render Box2DDebugLines
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        p1.draw(game.batch);
        game.batch.end();

        //FPS on screen
        game.FPSbatch.begin();
        FPSfont.draw(
                game.FPSbatch,
                "FPS: " + Gdx.graphics.getFramesPerSecond(),
                gamePort.getWorldWidth() / 2 + 20,
                gamePort.getWorldHeight() / 2 + 20
        );
        game.FPSbatch.end();

        //Hud draw
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

}
