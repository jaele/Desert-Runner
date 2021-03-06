package com.james.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.james.game.DesertRunner;
import com.james.game.sounds.Sfx;
import com.james.game.sprites.Man;
import com.james.game.sprites.Obstacle;
import com.badlogic.gdx.utils.Array;


/**
 * Created by James on 11/11/2017.
 */

public class PlayState extends State {



    private static final int GROUND_Y_OFFSET = -90;
    private static final int CACTI_COUNT = 4;
    private static final int CACTI_SPACING = 125;

    private static final int COIN_COUNT = 4;
    private static final int COIN_SPACING = 125;

    private Man man;
    private Texture bg;
    private Texture ground;
    private Texture signarrow;

    private Vector2 signarrowPos;
    private Vector2 groundPos1, groundPos2, groundPos3, groundPos4, groundPos5;

    private Array<Obstacle> cacti_1;
    private Array<Obstacle> coin;

    public static BitmapFont fontMedium;
    private Stage uiStage;
    private Label scoreLabel;
    private int score;

    public PlayState(GameStateManager gsm) {
        super(gsm);


        fontMedium = new BitmapFont(Gdx.files.internal("font/font.fnt"),
                Gdx.files.internal("font/font_0.png"), false);

        uiStage = new Stage(new StretchViewport(DesertRunner.WIDTH, DesertRunner.HEIGHT));

        scoreLabel = new Label("0", new Label.LabelStyle(fontMedium, Color.WHITE));
        scoreLabel.setPosition(DesertRunner.WIDTH/2, DesertRunner.HEIGHT * .9f, Align.center);
        uiStage.addActor(scoreLabel);

        man = new Man(50, 300);

        cam.setToOrtho(false, DesertRunner.WIDTH/2, DesertRunner.HEIGHT/2 );

        bg = new Texture("png/bg.png");
        ground = new Texture("png/tile/2.png");
        signarrow = new Texture("png/signarrow.png");

        signarrowPos = new Vector2(cam.position.x - cam.viewportWidth / 2, 32);
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        groundPos3 = new Vector2((cam.position.x - cam.viewportWidth / 2) + (ground.getWidth() *2), GROUND_Y_OFFSET);
        groundPos4 = new Vector2((cam.position.x - cam.viewportWidth / 2) + (ground.getWidth() *3), GROUND_Y_OFFSET);
        groundPos5 = new Vector2((cam.position.x - cam.viewportWidth / 2) + (ground.getWidth() *4), GROUND_Y_OFFSET);


        cacti_1 = new Array<Obstacle>();
        coin = new Array<Obstacle>();

        for(int i = 1; i < CACTI_COUNT; i++) {
            cacti_1.add(new Obstacle(i * (CACTI_SPACING + Obstacle.CACTI_WIDTH)));
        }

        for(int i = 1; i < COIN_COUNT; i++) {
            coin.add(new Obstacle(i * (COIN_SPACING + Obstacle.COIN_WIDTH)));
        }


    }


    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched() && !(man.aboveGround())) {
            man.jump();
            Sfx.PlayJumpSound();

        } else {
            return;
        }

    }

    @Override
    public void update(float dt) {

        handleInput();
        updateGround();
        man.update(dt);
        boolean gotHit = false;

        cam.position.x = man.getPosition().x + 80;


        for(Obstacle cacti: cacti_1) {
            if(cam.position.x - (cam.viewportWidth / 2) > cacti.getPosCacti_1().x +
                    cacti.getCacti_1().getWidth()) {
                cacti.reposition(cacti.getPosCacti_1().x + ((Obstacle.CACTI_WIDTH + CACTI_SPACING) *
                        CACTI_COUNT));
            }

            if(cacti.collides(man.getBounds())) {
                Sfx.PlayHitSound();

                gsm.set(new PlayState(gsm));
                man.getMovement();
                score = 0;
                break;
            }
        }

        for(Obstacle cn: coin) {

            gotHit = false;

            if(cam.position.x - (cam.viewportWidth / 2) > cn.getPosCoin().x +
                    cn.getCoin().getWidth()) {
                cn.reposition(cn.getPosCoin().x + ((Obstacle.COIN_WIDTH + COIN_SPACING) *
                        COIN_COUNT));
            }

            if(cn.collectsCoin(man.getBounds())) {
                cn.reposition(cn.getPosCoin().x + ((Obstacle.COIN_WIDTH + COIN_SPACING) *
                        COIN_COUNT));
                Sfx.PlayCoinSound();

                gotHit = true;
                man.increaseMovement();
            }
        }

        if(gotHit == true) {
            score++;
            updateScoreLabel();
        }



        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x  - (cam.viewportWidth /2), 0);
        sb.draw(man.getTexture(), man.getPosition().x, man.getPosition().y);

        for(Obstacle cacti: cacti_1) {
            sb.draw(cacti.getCacti_1(), cacti.getPosCacti_1().x, cacti.getPosCacti_1().y);
        }

        for(Obstacle cn: coin) {
            sb.draw(cn.getCoin(), cn.getPosCoin().x, cn.getPosCoin().y);
        }


        sb.draw(signarrow, signarrowPos.x, signarrowPos.y);
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.draw(ground, groundPos3.x, groundPos3.y);
        sb.draw(ground, groundPos4.x, groundPos4.y);
        sb.draw(ground, groundPos5.x, groundPos5.y);
        sb.end();

        uiStage.draw();
    }

    @Override
    public void dispose() {
        bg.dispose();
        man.dispose();
        signarrow.dispose();

        ground.dispose();
        for(Obstacle cacti: cacti_1) {
            cacti.dispose();
            System.out.println("Play State Dispose");
        }

        for(Obstacle cn: coin) {
            cn.dispose();
            System.out.println("Play State Dispose");
        }

        uiStage.dispose();
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

    private void updateScoreLabel() {
        scoreLabel.setText(String.valueOf(score));
    }
}
