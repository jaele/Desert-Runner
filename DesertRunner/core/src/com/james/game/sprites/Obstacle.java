package com.james.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by James on 12/11/2017.
 */

public class Obstacle {

    public static final int CACTI_WIDTH = 2;
    public static final int COIN_WIDTH = 1;


    private Vector2 positionCacti_1;
    private Vector2 positionCoin;

    private Rectangle boundCacti_1;
    private Rectangle boundCoin;

    private Texture cacti_1;
    private Texture coin;


    private Random rand;

    public Obstacle(int x) {

        rand = new Random();

        cacti_1 = new Texture("png/objects/cacti1.png");
        coin = new Texture("png/objects/coin.png");

        positionCacti_1 = new Vector2(35, 30);
        positionCoin = new Vector2(100, 70);

        boundCacti_1 = new Rectangle(positionCacti_1.x, positionCacti_1.y, cacti_1.getWidth(), cacti_1.getHeight());
        boundCoin = new Rectangle(positionCoin.x, positionCoin.y, coin.getWidth(), coin.getHeight());
    }


    public Texture getCacti_1(){
        return cacti_1;
    }

    public Texture getCoin() {
        return coin;
    }


    public Vector2 getPosCacti_1() {
        return positionCacti_1;
    }


    public Vector2 getPosCoin() {
        return positionCoin;
    }

    public void reposition(float x) {
        positionCacti_1.set(x, 30);
        positionCoin.set(x, 70);

        boundCacti_1.setPosition(positionCacti_1.x, positionCacti_1.y);
        boundCoin.setPosition(positionCoin.x, positionCoin.y);
    }
    public boolean collides (Rectangle player) {
        return player.overlaps(boundCacti_1);
    }

    public boolean collectsCoin (Rectangle player) {
        return player.overlaps(boundCoin);
    }

    public void dispose() {
        cacti_1.dispose();
        coin.dispose();
    }


}
