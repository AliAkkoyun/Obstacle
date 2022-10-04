package com.obstacleavoid.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.ObstacleAvoidGame;
import com.obstacleavoid.assets.AssetDescriptors;
import com.obstacleavoid.assets.RegionNames;
import com.obstacleavoid.common.GameManager;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.util.GdxUtils;

public class HighscoreScreen extends MenuScreenBase {

    private static final Logger log = new Logger(HighscoreScreen.class.getName(), Logger.DEBUG);
    private Sound click;

    public HighscoreScreen(ObstacleAvoidGame game) {
        super(game);
    }


        protected Actor createUi(){
        Table table = new Table();

        click = assetManager.get(AssetDescriptors.CLICK_SOUND);


        TextureAtlas gamePlayAtlas = assetManager.get(AssetDescriptors.PLAY_ASSETS);
        BitmapFont font = assetManager.get(AssetDescriptors.FONT);

        Skin uiskin = assetManager.get(AssetDescriptors.UI_SKIN);

        TextureRegion backgroundRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        //BACKGROUND
        table.setBackground(new TextureRegionDrawable(backgroundRegion));
        //HIGHSCORE TEXT
        Label highscoreText = new Label("HIGHSCORE",labelStyle);
        //HIGHSCORE LABEL
        String highScore = GameManager.INSTANCE.getHighScoreString();
        Label highscoreLabel = new Label(highScore,labelStyle);
        //BACK BUTTON
            TextButton backButton = new TextButton("BACK", uiskin);
            backButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    back();
                }
            });
        //SETUP TABLE
        Table contentTable = new Table(uiskin);
        contentTable.defaults().pad(20);
        contentTable.setBackground(RegionNames.MENU_BACKGROUND);

        contentTable.add(highscoreText).row();
        contentTable.add(highscoreLabel).row();
        contentTable.add(backButton).row();

        contentTable.center();

        table.add(contentTable);
        table.center();
        table.setFillParent(true);
        table.pack();

        return table;


    }

    private void back(){
        log.debug("back");
        click.play();
        game.setScreen(new MenuScreen(game));
    }


}
