package com.pachito.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pachito.game.FlappyGame;
import com.pachito.game.sprites.Font;
import com.pachito.game.sprites.Scoring;

public class InfoState extends State {

    private Scoring scoring;
    private Texture background;
    private Texture gameoverImg;
    private BitmapFont scoreFont;
    private BitmapFont maxScoreFont;
    private BitmapFont messageFont;

    public InfoState(GameStateManager gsm, Scoring scoring) {
        super(gsm);
        this.scoring = scoring;
        cam.setToOrtho(false, FlappyGame.WIDTH/2, FlappyGame.HEIGHT/2);
        background = new Texture("bg.png");
        gameoverImg = new Texture("gameover.png");
        Font font = new Font();
        scoreFont = font.getBitmap();
        messageFont = font.getBitmap();
        font.setColor(Color.GOLD);
        font.setSize(Font.SIZE_BIG);
        maxScoreFont = font.getBitmap();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        scoring.saveNewRecord();

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(gameoverImg, cam.position.x - gameoverImg.getWidth() / 2, cam.position.y + 100);

        String maxScoreText = "Record: " + Integer.toString(scoring.getMaxScore());
        maxScoreFont.draw(sb, maxScoreText, cam.position.x - getStringWidth(maxScoreFont, maxScoreText)/2, cam.position.y + 50);

        String scoreText = "Score: " + Integer.toString(scoring.getScore());
        scoreFont.draw(sb, scoreText, cam.position.x - getStringWidth(scoreFont, scoreText)/2, cam.position.y);

        messageFont.draw(sb, "Jeje, espero te \nhaya gustado y \nque lo disfrutes \nmucho ;)", cam.position.x / 4, cam.position.y - 30);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        scoring.dispose();
        scoreFont.dispose();
        maxScoreFont.dispose();
        System.out.println("Info State Disposed");
    }

    private float getStringWidth(BitmapFont font, String str) {
        GlyphLayout glyphLayout = new GlyphLayout(font, str);
        return glyphLayout.width;
    }
}
