package com.james.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by James on 12/11/2017.
 */

public class Obstacle {

    public static final int CACTI_WIDTH = 5;
    private Vector2 positionCacti_1;
    private Rectangle boundCacti_1;
    private Texture cacti_1;

    private Random rand;

    public Obstacle(int x) {

        rand = new Random();

        cacti_1 = new Texture("png/objects/cacti1.png");

        positionCacti_1 = new Vector2(35, 30);

        boundCacti_1 = new Rectangle(positionCacti_1.x, positionCacti_1.y, cacti_1.getWidth(), cacti_1.getHeight());
    }


    public Texture getCacti_1(){
        return cacti_1;
    }


    public Vector2 getPosCacti_1() {
        return positionCacti_1;
    }

    public void reposition(float x) {
        positionCacti_1.set(x, 30);

        boundCacti_1.setPosition(positionCacti_1.x, positionCacti_1.y);
    }
    public boolean collides (Rectangle player) {
        return player.overlaps(boundCacti_1);
    }

    public void dispose() {
        cacti_1.dispose();
    }
}
