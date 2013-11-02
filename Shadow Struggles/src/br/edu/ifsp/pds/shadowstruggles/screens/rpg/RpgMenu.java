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
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

public class RpgMenu extends BaseScreen{
	private TextButton status;
	private TextButton inventory;
	private TextButton saveGame;
	private TextButton settings;
	private TextButton decks;
	private Image background;

	public RpgMenu(ShadowStruggles game, Controller controller) {
		super(game, controller);
		initComponents();
	}
	
	@Override
	public void initComponents() {
		stage.clear();
		background = new Image(this.getSkin().getDrawable("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);
		
		status = new TextButton(MenuTextDAO.getMenuText().status, 
				getSkin());
		status =ScreenUtils.defineButton(status, 0, 0, 0,
				0, super.getSkin());
		status.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_4");
				StatusScreen statusScreen = new StatusScreen(game, controller);
				game.setScreenWithTransition(statusScreen);
			}
		});
		
		inventory = new TextButton(MenuTextDAO.getMenuText().inventory, 
				getSkin());
		inventory =ScreenUtils.defineButton(inventory, 0, 0, 0,
				0, super.getSkin());
		
		saveGame = new TextButton(MenuTextDAO.getMenuText().saveGame, 
				getSkin());
		saveGame =ScreenUtils.defineButton(saveGame, 0, 0, 0,
				0, super.getSkin());
		
		settings = new TextButton(MenuTextDAO.getMenuText().settings, 
				getSkin());
		settings =ScreenUtils.defineButton(settings, 0, 0, 0,
				0, super.getSkin());
		
		decks = new TextButton(MenuTextDAO.getMenuText().decks, 
				getSkin());
		decks =ScreenUtils.defineButton(decks, 0, 0, 0,
				0, super.getSkin());
		
		//Table
		Table menuTable = new Table();
		menuTable.defaults().width(480).height(110).padTop(10);
		if (game.getMode() == RunMode.DEBUG)
			menuTable.debug();
		menuTable.defaults();
		
		menuTable.add(status);
		menuTable.row();
		menuTable.add(inventory);
		menuTable.row();
		menuTable.add(saveGame);
		menuTable.row();
		menuTable.add(settings);
		menuTable.row();
		menuTable.add(decks);
		menuTable.setPosition(480,330);
		
		stage.addActor(background);
		stage.addActor(menuTable);
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		Table.drawDebug(stage);
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

}
