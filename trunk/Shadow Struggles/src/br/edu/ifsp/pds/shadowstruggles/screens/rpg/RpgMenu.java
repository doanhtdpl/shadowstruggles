package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.rpg.RpgController;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.LoadingScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.MainScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.SaveLoadScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.SaveLoadScreen.Mode;
import br.edu.ifsp.pds.shadowstruggles.screens.SettingsScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

public class RpgMenu extends BaseScreen{
	
	private TextButton status;
	private TextButton inventory;
	private TextButton loadGame;
	private TextButton settings;
	private TextButton decks;
	private TextButton returnButton;
	private BaseScreen previousScreen;

	public RpgMenu(BaseScreen previousScreen) {		
		super(ShadowStruggles.getInstance(), ShadowStruggles.getInstance().getController());
		this.previousScreen=previousScreen;
		initComponents();
	}
	
	@Override
	public void initComponents() {
		stage.clear();
		
		status = new TextButton(MenuTextDAO.getMenuText().status, 
				getSkin());
		status =ScreenUtils.defineButton(status, 0, 0, 0,
				0, super.getSkin());
		status.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				StatusScreen statusScreen = new StatusScreen(getThis());
				game.setScreenWithTransition(statusScreen);
			}
		});
		
		inventory = new TextButton(MenuTextDAO.getMenuText().inventory, 
				getSkin());
		inventory =ScreenUtils.defineButton(inventory, 0, 0, 0,
				0, super.getSkin());
		
		loadGame = new TextButton(MenuTextDAO.getMenuText().loadGame, 
				getSkin());
		loadGame =ScreenUtils.defineButton(loadGame, 0, 0, 0,
				0, super.getSkin());
		loadGame.addListener(new ClickListener(){@Override
		public void clicked(InputEvent event, float x, float y) {
			game.getAudio().playSound("button_4");
			SaveLoadScreen loadScreen = new SaveLoadScreen(game,controller,Mode.LOAD,getThis());
			game.setScreenWithTransition(loadScreen);
		}});
		settings = new TextButton(MenuTextDAO.getMenuText().settings, 
				getSkin());
		settings =ScreenUtils.defineButton(settings, 0, 0, 0,
				0, super.getSkin());
		settings.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				SettingsScreen settingsScreen = new SettingsScreen(game, controller, getThis());
				game.setScreenWithTransition(settingsScreen);
			}
		});
		
		decks = new TextButton(MenuTextDAO.getMenuText().decks, 
				getSkin());
		decks =ScreenUtils.defineButton(decks, 0, 0, 0,
				0, super.getSkin());
		
		returnButton = new TextButton(MenuTextDAO.getMenuText().returnToStart,
				super.getSkin());
		returnButton = ScreenUtils.defineButton(returnButton, 0, 0, 0, 0,
				super.getSkin());
		returnButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				 RpgScreen previousRpgScreen = (RpgScreen) previousScreen;
			       Controller controller = previousRpgScreen.getController();
			       RpgController rpgController = previousRpgScreen.getRpgController();

			       RpgScreen nextScreen = new RpgScreen(game, controller,
			         rpgController);
			       game.setScreenWithTransition(new LoadingScreen(
			         game, nextScreen));

			}
		});
		
		//Table
		Table menuTable = new Table();
		menuTable.defaults().width(480).height(90).padTop(10);
		if (game.getMode() == RunMode.DEBUG)
			menuTable.debug();
		menuTable.defaults();
		
		menuTable.add(status);
		menuTable.row();
		menuTable.add(inventory);
		menuTable.row();
		menuTable.add(loadGame);
		menuTable.row();
		menuTable.add(settings);
		menuTable.row();
		menuTable.add(decks);
		menuTable.row();
		menuTable.add(returnButton);
		menuTable.setPosition(480,330);
		
		stage.addActor(menuTable);
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		Table.drawDebug(stage);
	}
	
	public RpgMenu getThis(){
		return this;
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

}
