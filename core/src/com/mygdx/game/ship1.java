package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ship1 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture ship[]=new Texture[4];
    Texture bg;
    Texture enemigo;
    Texture ammo;
    Texture go;
    Sound laser;

    int frame, texturaactual;
    int velocidadbg, velocidadenemigo, velocidadammo;
    boolean gameover;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ship[0] = new Texture("1.png");
        ship[1] = new Texture("2.png");
        ship[2] = new Texture("3.png");
        ship[3] = new Texture("4.png");
        bg = new Texture("bg.png");
        enemigo = new Texture("enemigo.png");
        ammo = new Texture("ammo.png");
        go = new Texture("gameover.png");
        laser = Gdx.audio.newSound(Gdx.files.internal("laser.mp3"));
        frame=0;
        texturaactual=0;
        velocidadbg=0;
        velocidadenemigo=500;
        velocidadammo=100;
        gameover=false;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        batch.draw(bg, 0, velocidadbg+960);
        batch.draw(bg, 0, velocidadbg);
        batch.draw(enemigo, 280, velocidadenemigo);
        batch.draw(ammo, 296, velocidadammo);
        batch.draw(ship[texturaactual], 220, -30);
        if(gameover){
            batch.draw(go, 0, 0);
            if(Gdx.input.isTouched()){
                gameover=false;
            }
        }
		batch.end();

        if(Gdx.input.isTouched() && gameover==false){
            if(velocidadammo==100&&Gdx.input.isTouched()) {
                laser.play();
            }
            velocidadammo+=12;
            if(velocidadammo==520){
                velocidadammo=100;
            }
        }
        else
            velocidadammo=100;

        if(velocidadammo+27>=velocidadenemigo&&velocidadenemigo<460){
            velocidadammo=100;
            velocidadenemigo=600;
        }

        if(velocidadenemigo<ship[texturaactual].getHeight()){
            gameover=true;
        }

        if(frame%3==0) {
            texturaactual++;
            if (texturaactual > 2) {
                texturaactual = 0;
            }
        }
        velocidadbg-=3;
        if(velocidadbg==-960){
            velocidadbg=0;
        }

        velocidadenemigo-=2;
        if(velocidadenemigo==-40){
            velocidadenemigo=500;
        }
        frame++;
	}
}
