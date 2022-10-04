package com.obstacleavoid.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.obstacleavoid.ObstacleAvoidGame;
import com.obstacleavoid.assets.AssetDescriptors;
import com.obstacleavoid.assets.RegionNames;
import com.obstacleavoid.common.GameManager;
import com.obstacleavoid.config.DifficultyLevel;
import com.obstacleavoid.config.GameConfig;
import com.obstacleavoid.util.GdxUtils;



public class OptionsScreen extends MenuScreenBase {

    private static final Logger log = new Logger(OptionsScreen.class.getName(), Logger.DEBUG);

    private ButtonGroup<CheckBox> checkBoxGroup;
    private CheckBox easy;
    private CheckBox medium;
    private CheckBox hard;

    public OptionsScreen(ObstacleAvoidGame game) {
        super(game);

    }

    protected Actor createUi(){
        Table table = new Table();
        table.defaults().pad(20);

        TextureAtlas gamePlayAtlas = assetManager.get(AssetDescriptors.PLAY_ASSETS);
        Skin uiskin = assetManager.get(AssetDescriptors.UI_SKIN);

        TextureRegion backgroundRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);
        table.setBackground(new TextureRegionDrawable(backgroundRegion));

        //label
        Label label = new Label("   DIFFICULTIES   ",uiskin);


        easy = checkBox("EASY",uiskin);
        medium = checkBox("MEDIUM",uiskin);
        hard = checkBox("HARD",uiskin);

        checkBoxGroup = new ButtonGroup<CheckBox>(easy,medium,hard);

        final DifficultyLevel difficultyLevel = GameManager.INSTANCE.getDifficultyLevel();
        checkBoxGroup.setChecked(difficultyLevel.name());

        final TextButton backButton = new TextButton("BACK",uiskin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });

        ChangeListener listener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                difficultyChanged();
            }
        };

        easy.addListener(listener);
        medium.addListener(listener);
        hard.addListener(listener);

        //setup table

        Table contentTable = new Table(uiskin);
        contentTable.defaults().pad(10);
        contentTable.setBackground(RegionNames.MENU_BACKGROUND);

        contentTable.add(label).row();
        contentTable.add(easy).row();
        contentTable.add(medium).row();
        contentTable.add(hard).row();
        contentTable.add(backButton);

        table.add(contentTable);
        table.center();
        table.setFillParent(true);
        table.pack();

        return table;
    }
    private void back(){
        log.debug("geri");
        game.setScreen(new MenuScreen(game));
    }
    private void difficultyChanged(){
        log.debug("seçildi");
        CheckBox checked = checkBoxGroup.getChecked();

        if(checked == easy){
            GameManager.INSTANCE.updateDifficulty(DifficultyLevel.EASY);
        } else if(checked == medium){
            GameManager.INSTANCE.updateDifficulty(DifficultyLevel.MEDIUM);
        } else if(checked == hard){
            GameManager.INSTANCE.updateDifficulty(DifficultyLevel.HARD);
        }

    }
    private static CheckBox checkBox(String text,Skin skin){
        CheckBox checkBox = new CheckBox(text,skin);
        checkBox.left().padLeft(10);
        checkBox.getLabelCell().padLeft(20);
        return checkBox;
    }
}
