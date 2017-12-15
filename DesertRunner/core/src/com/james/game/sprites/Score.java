package com.james.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by James on 12/14/2017.
 */

public class Score {

    public static SpriteBatch batch;
    public static BitmapFont fontMedium;


    public static void load() {


        batch = new SpriteBatch();
        fontMedium = new BitmapFont(Gdx.files.internal("font/font.fnt"),
                Gdx.files.internal("font/font_0.png"), false);
    }

    public void dispose() {
        fontMedium.dispose();
    }

}
