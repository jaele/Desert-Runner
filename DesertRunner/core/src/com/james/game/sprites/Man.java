package com.james.game.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by James on 11/11/2017.
 */

public class Man {

    private static final int GRAVITY = -15;
    private static float MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation manAnimation;
    private Texture texture;
    private Sound run;

    public Man(int x, int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("runani.png");
        manAnimation = new Animation(new TextureRegion(texture), 10, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 10, texture.getHeight());
    }

    public void update (float dt) {

        manAnimation.update(dt);
        if(position.y > 0) {

            velocity.add(0, GRAVITY, 0 );
        }

        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        if(position.y < 32) {
            position.y = 32;
        }

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
        MOVEMENT = MOVEMENT + 0.5f;
    }


    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return manAnimation.getFrame();
    }

    public void jump() {
        velocity.y = 350;
    }

    public float getMovement() {
        MOVEMENT = 100;
        return MOVEMENT;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }

    public boolean aboveGround() {
        if(position.y == 32) {
            return false;
        } else {
            return true;
        }
    }
}
