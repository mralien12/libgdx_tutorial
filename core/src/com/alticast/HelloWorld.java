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
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

	public class MyActor  extends Actor {
		Texture texture = new Texture(Gdx.files.internal("data/jet.png"));
		public boolean started = false;

		public MyActor() {
			setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(texture,this.getX(),getY(),this.getOriginX(),this.getOriginY(),this.getWidth(),
					this.getHeight(),this.getScaleX(), this.getScaleY(),this.getRotation(),0,0,
					texture.getWidth(),texture.getHeight(),false,false);
		}

		@Override
		public void act(float delta){
			for(Iterator<Action> iter = this.getActions().iterator(); iter.hasNext();){
				iter.next().act(delta);
			}
		}
	}

	private Stage stage;
	@Override
	public void create () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		MyActor myActor = new MyActor();
//		SequenceAction sequenceAction = new SequenceAction();
//
//		MoveToAction moveAction = new MoveToAction();
//		RotateToAction rotateAction = new RotateToAction();
//		ScaleToAction scaleAction = new ScaleToAction();
//
//		moveAction.setPosition(300f, 0f);
//		moveAction.setDuration(5f);
//		rotateAction.setRotation(90f);
//		rotateAction.setDuration(5f);
//		scaleAction.setScale(0.5f);
//		scaleAction.setDuration(5f);
//
//		sequenceAction.addAction(scaleAction);
//		sequenceAction.addAction(rotateAction);
//		sequenceAction.addAction(moveAction);

//		myActor.addAction(sequence(scaleTo(0.5f,0.5f,5f),rotateTo(90.0f,5f),moveTo(300.0f,0f,5f)));
		myActor.addAction(parallel(scaleTo(0.5f,0.5f,5f),rotateTo(90.0f,5f),moveTo(300.0f,0f,5f)));
		stage.addActor(myActor);
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
