package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.SceneDAO;
import br.edu.ifsp.pds.shadowstruggles.games.Practice;
import br.edu.ifsp.pds.shadowstruggles.model.Scene;
import br.edu.ifsp.pds.shadowstruggles.object2d.TransitionControl;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

//TODO: Adicionar opção de salvar o jogo.

public class SceneScreen extends BaseScreen implements InputProcessor {
	private Scene scene;
	private int touchY;
	private ShadowStruggles game;
	private boolean touched;
	private boolean justTouched;

	private Image background;
	private Label text;
	private Image next;
	private TextButton[] choices;
	private Image box;
	private Image nextDialog;
	private TextButton menu;
	private String currentText;
	private int currentTextIndex;
	private Object[] target;
	
	

	private InputMultiplexer inputSources;

	public SceneScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		this.game = game;
		this.scene = game.getProfile().getCurrentScene();
		currentTextIndex=0;
		inputSources = new InputMultiplexer();
		initComponents();

	}

	private void initComponents() {
		
		currentText=splitScript()[currentTextIndex];

		this.text = new Label(currentText, super.getSkin());
		
		if (!scene.getBackgroundMusic().equals("")) {
			game.getAudio().stop();
			game.getAudio().setMusic(scene.getBackgroundMusic());
		} else {
			game.getAudio().stop();
		}
		
		if (!scene.getBackgroundImage().equals("")) {
			if (scene.getBackgroundImage().contains("/maps/")) {
				background = new Image(new TextureRegion(new Texture(
						Gdx.files.internal(scene.getBackgroundImage())),
						settings.backgroundWidth / 2,
						settings.backgroundHeight / 2));
			} else {
				background = new Image(new Texture(Gdx.files.internal(scene
						.getBackgroundImage())));
			}
		} else
			background = new Image();

		background.setX(0);
		background.setY(0);
		background.setWidth(960);
		background.setHeight(640);
		
		text.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.WHITE));
		text.setWidth(800);
		text.setWrap(true);
		text.setHeight(text.getPrefHeight());
		text.setX(80);
		text.setY(580 - text.getHeight() - 50);
		text.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				nextText();
				super.clicked(event, x, y);
			}
		});
		box = new Image(game.getAssets()
				.get("data/images/objects/objects.atlas", TextureAtlas.class)
				.findRegion("box"));
		box.setWidth(text.getWidth() + 50);
		//box.setHeight(text.getHeight() + 50);
		box.setHeight(500);
		box.setX(55);
		//box.setY(text.getY() - 25);
		box.setY(50);
		box.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				nextText();
				super.clicked(event, x, y);
			}
		});

		next = new TransitionControl(1, this.getSkin());
		next.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {				
				nextText();
				super.clicked(event, x, y);
				game.getAudio().playSound("button_7");
			}
		});

		next.setScaleY(6.0f);
		//TODO: adicionar botão nextDialog para avançar os dialogos
		nextDialog= new Image();

		menu = new TextButton("M", getSkin());
		menu = ScreenUtils.defineButton(menu, next.getX() - 15, 70, 50, 50, super.getSkin());
		menu.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(new MainScreen(game, controller));
			}

		});

		inputSources.addProcessor(this.stage);
		inputSources.addProcessor(this);

		stage.addActor(background);
		stage.addActor(box);
		stage.addActor(text);
		stage.addActor(next);
		stage.addActor(menu);

		if (scene.getChoices().length > 1) {
			choices = new TextButton[scene.getChoices().length];
			for (int i = 0; i < scene.getChoices().length; i++) {
				final int j = i;
				choices[i] = new TextButton(scene.getChoices()[i],
						super.getSkin());

				choices[i] = ScreenUtils.defineButton(choices[i], box.getX()
						+ (i * 220), box.getY() - 100, 200, 80, super.getSkin());
				choices[i].addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (choices[j].getText().equals(scene.getChoices()[0])) {
							game.getProfile().setCurrentScene(
									SceneDAO.getScene(scene.getNextId(),
											game.getManager()));

						} else {
							game.getProfile().setCurrentScene(
									SceneDAO.getScene(scene.getNextId() + 1,
											game.getManager()));
						}
						game.getAudio().playSound("button_4");

					}
				});
				stage.addActor(choices[i]);
			}
		} else
			stage.addActor(next);
	}
	
	private void nextText(){
		currentTextIndex++;		
		updateText();
	}
	
	private void updateText(){
		if(currentTextIndex < splitScript().length){
			currentText=splitScript()[currentTextIndex];
			System.out.println(currentText.length());
			if(currentText.length()>2){
				if((splitScript()[currentTextIndex]+ 
						splitScript()[currentTextIndex-1]).length()<=500)
					text.setText((splitScript()[currentTextIndex-1]+ 
							splitScript()[currentTextIndex]));
				else
			text.setText(currentText);
			text.setHeight(text.getPrefHeight());
			text.setY(580 - text.getHeight() - 50);
			}else {
				nextText();
			}
			
		}else {
			if(scene.getChoices().length<2)
				nextScreen();
			else showChoices();
		}
	}
	
	
	private void showChoices(){
		TextButton choice1 = new TextButton(scene.getChoices()[0], this.getSkin());
		TextButton choice2 = new TextButton(scene.getChoices()[1], this.getSkin());
		choice1.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//TODO: verificar target[0] e mandar para próxima tela de acordo com a classe do target 
				super.clicked(event, x, y);
			}
		});
		choice2.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//TODO: verificar target[1] e mandar para próxima tela de acordo com a classe do target 
				super.clicked(event, x, y);
			}
		});
		choice1= ScreenUtils.defineButton(choice1, 50, 400, 700, 200, this.getSkin());
		choice2= ScreenUtils.defineButton(choice1, 50, 100, 700, 200, this.getSkin());
		text.remove();
		stage.addActor(choice1);
		stage.addActor(choice2);
	}

	public void nextScreen() {
		if (scene.getId() == 1000) {
			game.setScreenWithTransition(new Practice(game, true));
		} else if (scene.getId() == 3000) {
			Scene firstScene = Scene.FIRST_SCENE;
			firstScene.setLanguage(game.getProfile().getLanguage());
			game.getProfile().setCurrentScene(firstScene);
			game.getManager().writeProfile(game.getProfile());
			game.setScreenWithTransition(new MainScreen(game, controller));
		} else {
			game.getProfile().setCurrentScene(
					SceneDAO.getScene(scene.getNextId(), game.getManager()));
			game.getManager().writeProfile(game.getProfile());
			game.setScreenWithTransition(new SceneScreen(game, controller));
		}
	}
	
	private String[] splitScript(){
		
		return scene.getScript().split("/:/");
		
	}

	@Override
	public void resize(int width, int height) {

		super.resize(width, height);

		Gdx.input.setInputProcessor(inputSources);

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		background.setY(this.camera.position.y - CAMERA_INITIAL_Y);
		next.setY(this.camera.position.y - CAMERA_INITIAL_Y + 320);
		menu.setY(this.camera.position.y - CAMERA_INITIAL_Y + 20);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));

		touched = true;

		if (!justTouched) {
			justTouched = true;
			touchY = y;
		}

		return true;

	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {

		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));

		if (touched) {

			touched = false;
		}

		justTouched = false;

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		x = (int) (x * (float) ((float) controller.getCurrentScreen()
				.getSettings().screenWidth / (float) controller
				.getCurrentScreen().getWidth()));
		if (touched) {

			if (controller.getCurrentScreen().camera.position.y - touchY + y < CAMERA_INITIAL_Y
					&& controller.getCurrentScreen().camera.position.y - touchY
							+ y > +640 - text.getHeight()) {
				controller.getCurrentScreen().camera.position.y -= touchY - y;

				// Render the screen again to avoid blinking.
				this.render(1 / 60);

			}
			this.touchY = y;
			return true;

		}

		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.DOWN) {
			if (controller.getCurrentScreen().camera.position.y - 4 < CAMERA_INITIAL_Y
					&& controller.getCurrentScreen().camera.position.y
							- 4 > +640 - text.getHeight()) {
				controller.getCurrentScreen().camera.position.y -= 4;

				// Render the screen again to avoid blinking.
				this.render(1 / 60);

			}
			return true;
		}
		
		if(keycode == Keys.UP) {
			if (controller.getCurrentScreen().camera.position.y + 4 < CAMERA_INITIAL_Y
					&& controller.getCurrentScreen().camera.position.y
							+ 4 > +640 - text.getHeight()) {
				controller.getCurrentScreen().camera.position.y += 4;

				// Render the screen again to avoid blinking.
				this.render(1 / 60);

			}
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		this.nextScreen();
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
}
