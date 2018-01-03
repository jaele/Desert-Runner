package com.james.game.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by James on 1/2/2018.
 */

public class Sfx {
    private static Sound hitSound;
    private static long hitID;

    private static Sound jumpSound;
    private static long jumpID;

    private static Sound coinSound;
    private static long coinID;

    public static void Initialize() {
        hitSound = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
        hitID = hitSound.play(0);

        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
        jumpID = jumpSound.play(0);

        coinSound = Gdx.audio.newSound(Gdx.files.internal("coincollect.wav"));
        coinID = coinSound.play(0);
    }

    public static void PlayHitSound() {
        hitSound.stop(hitID);
        hitID = hitSound.play(0.5f);
    }

    public static void PlayJumpSound() {
        jumpSound.stop(jumpID);
        jumpID = jumpSound.play(0.3f);

    }

    public static void PlayCoinSound() {
        coinSound.stop(coinID);
        coinID = coinSound.play(0.5f);
    }
}
