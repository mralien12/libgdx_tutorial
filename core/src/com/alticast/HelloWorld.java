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
import java.util.Random;

import javax.xml.soap.Text;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class HelloWorld implements ApplicationListener {

	class Jet extends Actor {
		private TextureRegion _texture;

		public Jet(TextureRegion texture) {
			_texture = texture;
			setBounds(getX(), getY(), _texture.getRegionWidth(), _texture.getRegionHeight());

			this.addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					System.out.println("Touched " + getName());
					setVisible(false);
					return true;
				}
			});
		}

		// Implement the full form of draw() so we can handle rotation and scaling.
		public void draw(Batch batch, float alpha){
			batch.draw(_texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
					getScaleX(), getScaleY(), getRotation());
		}

		public Actor hit(float  x, float y, boolean touchable) {
			if (!this.isVisible() || this.getTouchable() == Touchable.disabled) {
				return null;
			}

			float centerX = getWidth()/2;
			float centerY = getHeight()/2;

			float radius  = (float) Math.sqrt(centerX  * centerX + centerY*centerY);
			float distance = (float) Math.sqrt((centerX - x)*(centerX-x) + (centerY-y)*(centerY-y));
			if (distance <= radius)
				return this;

			return null;
		}
	}

	private Jet[] jets;
	private Stage stage;

	@Override
	public void create () {
		stage = new Stage();
		final TextureRegion jetTexture = new TextureRegion(new Texture("data/jet.png"));

		jets = new Jet[10];

		// Create/seed our random number for positioning jets randomly
		Random random = new Random();

		// Create 10 Jet objects at random on screen locations
		for(int i = 0; i < 10; i++){
			jets[i] = new Jet(jetTexture);

			//Assign the position of the jet to a random value within the screen boundaries
			jets[i].setPosition(random.nextInt(Gdx.graphics.getWidth() - (int)jets[i].getWidth())
					, random.nextInt(Gdx.graphics.getHeight() - (int)jets[i].getHeight()));

			// Set the name of the Jet to it's index within the loop
			jets[i].setName(Integer.toString(i));

			// Add them to the stage
			stage.addActor(jets[i]);
		}

		Gdx.input.setInputProcessor(stage);
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
