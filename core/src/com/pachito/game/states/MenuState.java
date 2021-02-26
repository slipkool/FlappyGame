package com.pachito.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pachito.game.FlappyGame;
import com.pachito.game.sprites.Font;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private BitmapFont messageFont;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyGame.WIDTH/2, FlappyGame.HEIGHT/2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        Font font = new Font();
        messageFont = font.getBitmap();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) gsm.set(new PlayState(gsm));
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/2, cam.position.y);
        messageFont.draw(sb, "Hola Diana, \nespero te guste \neste pequeño \ndetalle con mucho \ncariño :)", (cam.viewportWidth / 4) - 20, cam.position.y - 10);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
