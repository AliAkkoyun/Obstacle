package com.obstacleavoid.screen.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.ObstacleAvoidGame;
import com.obstacleavoid.assets.AssetDescriptors;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.screen.game.GameScreen;
import com.obstacleavoid.screen.menu.MenuScreen;
import com.obstacleavoid.util.GdxUtils;


public class LoadingScreen extends ScreenAdapter {

    // == constants ==
    private static final Logger log = new Logger(LoadingScreen.class.getName(), Logger.DEBUG);

    private static final float PROGRESS_BAR_WIDTH = GameConfig.HI_WIDTH / 2f; // world units
    private static final float PROGRESS_BAR_HEIGHT = 60; // world units

    // == attributes ==
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private SpriteBatch batch;
    private Texture texture;


    private float progress;
    private float waitTime = 0.75f;
    private boolean changeScreen;

    private final ObstacleAvoidGame game;
    private final AssetManager assetManager;


    // == constructors ==
    public LoadingScreen(ObstacleAvoidGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    // == public methods ==
    @Override
    public void show() {
        log.debug("show");
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.HI_WIDTH, GameConfig.HI_HEIGHT, camera);
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("loading/LoadingScreen.png"));

        assetManager.load(AssetDescriptors.FONT);
        assetManager.load(AssetDescriptors.PLAY_ASSETS);
        assetManager.load(AssetDescriptors.UI_ASSETS);
        assetManager.load(AssetDescriptors.UI_SKIN);
        assetManager.load(AssetDescriptors.HIT_SOUND);
        assetManager.load(AssetDescriptors.CLICK_SOUND);
        assetManager.load(AssetDescriptors.NEW_HIGHSCORE_SOUND);
        assetManager.load(AssetDescriptors.MENU_SOUND);
    }

    @Override
    public void render(float delta) {
        update(delta);

        GdxUtils.clearScreen();
        viewport.apply();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        draw();
        batch.end();


        if(changeScreen) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        log.debug("hide");
        // NOTE: screens dont dispose automatically
        dispose();
    }

    @Override
    public void dispose() {
        log.debug("dispose");
        renderer.dispose();
        renderer = null;
    }

    // == private methods ==
    private void update(float delta) {

        progress = assetManager.getProgress();


        // update returns true when all assets are loaded
        if(assetManager.update()) {
            waitTime -= delta;

            if(waitTime <= 0) {
                changeScreen = true;
            }
        }
    }

    private void draw() {
        float progressBarX = (GameConfig.HI_WIDTH - PROGRESS_BAR_WIDTH) / 2f;
        float progressBarY = (GameConfig.HI_HEIGHT - PROGRESS_BAR_HEIGHT) / 2f;



        batch.draw(texture,progressBarX,progressBarY,
                 PROGRESS_BAR_WIDTH,PROGRESS_BAR_HEIGHT);
        //waitMillis(1000);

    }

    private static void waitMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
