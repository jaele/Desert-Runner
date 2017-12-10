package com.james.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.james.game.DesertRunner;
import com.james.game.sprites.Man;

/**
 * Created by James on 11/11/2017.
 */

public class PlayState extends State {

    private static final int GROUND_Y_OFFSET = -90;

    private Man man;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2, groundPos3, groundPos4, groundPos5;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        man = new Man(50, 300);
        cam.setToOrtho(false, DesertRunner.WIDTH/2, DesertRunner.HEIGHT/2 );
        bg = new Texture("png/bg.png");
        ground = new Texture("png/tile/2.png");
        //groundPos1 = new Vector2(cam.position.x + ground.getWidth(), GROUND_Y_OFFSET);
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        groundPos3 = new Vector2((cam.position.x - cam.viewportWidth / 2) + (ground.getWidth() *2), GROUND_Y_OFFSET);
        groundPos4 = new Vector2((cam.position.x - cam.viewportWidth / 2) + (ground.getWidth() *3), GROUND_Y_OFFSET);
        groundPos5 = new Vector2((cam.position.x - cam.viewportWidth / 2) + (ground.getWidth() *4), GROUND_Y_OFFSET);
    }


    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched() && !(man.aboveGround())) {
            man.jump();
        } else {
            return;
        }

        //if(Gdx.input.justTouched()) {
         //   man.jump();
       // }
    }

    @Override
    public void update(float dt) {

        handleInput();
        updateGround();
        man.update(dt);
        cam.position.x = man.getPosition().x + 80;
        cam.update();
    }



    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x  - (cam.viewportWidth /2), 0);
        sb.draw(man.getTexture(), man.getPosition().x, man.getPosition().y);
        //sb.draw(man.getJump(), man.getPosition().x, man.getPosition().y);

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.draw(ground, groundPos3.x, groundPos3.y);
        sb.draw(ground, groundPos4.x, groundPos4.y);
        sb.draw(ground, groundPos5.x, groundPos5.y);


        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        man.dispose();
        ground.dispose();

    }

    private void updateGround() {

        if(cam.position.x - (cam.viewportWidth / 2 ) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 5, 0);
        }

        if(cam.position.x - (cam.viewportWidth / 2 ) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 5, 0);
        }

        if(cam.position.x - (cam.viewportWidth / 2 ) > groundPos3.x + ground.getWidth()) {
            groundPos3.add(ground.getWidth() * 5, 0);
        }

        if(cam.position.x - (cam.viewportWidth / 2 ) > groundPos4.x + ground.getWidth()) {
            groundPos4.add(ground.getWidth() * 5, 0);
        }

        if(cam.position.x - (cam.viewportWidth / 2 ) > groundPos5.x + ground.getWidth()) {
            groundPos5.add(ground.getWidth() * 5, 0);
        }
    }
}
