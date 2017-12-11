package com.james.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by James on 12/11/2017.
 */

public class Obstacle {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 positionCacti_1;
    private Vector3 velocityCacti_1;
    private Rectangle boundCacti_1;
    private Texture cacti_1;

    private Random rand;

    public Obstacle(int x, int y) {
        positionCacti_1 = new Vector3(x,y,0);
        velocityCacti_1 = new Vector3(0,0,0);
        cacti_1 = new Texture("png/objects/cacti1.png");
        boundCacti_1 = new Rectangle(x, y, cacti_1.getWidth() / 10, cacti_1.getHeight());
    }

    public void update (float dt) {

        if(positionCacti_1.y > 0) {

            velocityCacti_1.add(0, GRAVITY, 0 );
        }

        velocityCacti_1.scl(dt);
        positionCacti_1.add(MOVEMENT * dt, velocityCacti_1.y, 0);

        if(positionCacti_1.y < 32) {
            positionCacti_1.y = 32;
        }

        velocityCacti_1.scl(1/dt);
        boundCacti_1.setPosition(positionCacti_1.x, positionCacti_1.y);
    }


    public Vector3 getPosCacti_1() {
        return positionCacti_1;
    }

    public Texture getCacti_1(){
        return cacti_1;
    }

    public Rectangle getBoundCacti1() {
        return boundCacti_1;
    }

    public boolean collides (Rectangle player) {
        return true;
    }

    public void dispose() {
        cacti_1.dispose();
    }
}
