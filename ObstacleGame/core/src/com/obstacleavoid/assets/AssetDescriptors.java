package com.obstacleavoid.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class AssetDescriptors {

    public static final AssetDescriptor<BitmapFont> FONT =
            new AssetDescriptor<BitmapFont>(AssetsFont.HI_FONTS, BitmapFont.class);

    public static final AssetDescriptor<TextureAtlas> PLAY_ASSETS =
            new AssetDescriptor<TextureAtlas>(AssetsFont.GAME_ASSETS, TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> UI_ASSETS =
            new AssetDescriptor<TextureAtlas>(AssetsFont.UI, TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> GAME_PAUSE_MENU =
            new AssetDescriptor<TextureAtlas>(AssetsFont.GAME_PAUSE_MENU, TextureAtlas.class);

    public static final AssetDescriptor<Skin> UI_SKIN =
            new AssetDescriptor<Skin>(AssetsFont.UI_SKIN, Skin.class);

    public static final AssetDescriptor<Sound> HIT_SOUND =
            new AssetDescriptor<Sound>(AssetsFont.HIT_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> MENU_SOUND =
            new AssetDescriptor<Sound>(AssetsFont.MENU_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> CLICK_SOUND =
            new AssetDescriptor<Sound>(AssetsFont.CLICK_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> NEW_HIGHSCORE_SOUND =
            new AssetDescriptor<Sound>(AssetsFont.NEW_HIGHSCORE_SOUND, Sound.class);


    public AssetDescriptors() {
    }
}
