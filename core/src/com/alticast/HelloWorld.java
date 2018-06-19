package com.alticast;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import java.util.Iterator;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class HelloWorld implements ApplicationListener {
	private Stage stage;
	private Group group;

	@Override
	public void create () {
		stage = new Stage();
		final TextureRegion jetTexture  = new TextureRegion(new Texture("data/jet.png"));
		final TextureRegion flameTexture = new TextureRegion(new Texture("data/flame.png"));

		final Actor jet = new Actor() {
			public void draw(Batch batch, float alpha) {
				batch.draw(jetTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
						getScaleX(), getScaleY(), getRotation());
			}
		};
		jet.setBounds(jet.getX(), jet.getY(), jetTexture.getRegionWidth(), jetTexture.getRegionHeight());

		final Actor flame = new Actor(){
			public void draw(Batch batch, float alpha){
				batch.draw(flameTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
						getScaleX(), getScaleY(), getRotation());
			}
		};
		flame.setBounds(0, 0, flameTexture.getRegionWidth(), flameTexture.getRegionHeight());
		flame.setPosition(jet.getWidth()-30, 75);

		group = new Group();
		group.addActor(jet);
		group.addActor(flame);
		group.addAction(parallel(moveTo(200, 0, 5), rotateBy(90, 5)));
		stage.addActor(group);
	}

	@Override
	public void dispose () {
		stage.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}
}
