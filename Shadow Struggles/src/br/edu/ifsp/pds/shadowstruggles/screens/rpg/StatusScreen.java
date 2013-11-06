package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;
import br.edu.ifsp.pds.shadowstruggles.data.FileMap;
import br.edu.ifsp.pds.shadowstruggles.data.dao.MenuTextDAO;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

public class StatusScreen extends BaseScreen{
	private static final int COLUMN = 300;
	private static final int ROW = 60;
	private static final int BAR_WIDTH = 550;
	
	private TextButton maxEnergy;
	private TextButton energyRecovery;
	private TextButton deckCapacity;
	private TextButton maxCardPoints;
	private TextButton maxHealth;
	private TextButton doubleDraw;
	private TextButton returnButton;
	private StatusBar maxEnergyBar;
	private Image energyRecoveryBar;
	private Image deckCapacityBar;
	private Image maxCardPointsBar;
	private Image maxHealthBar;
	private Image doubleDrawBar;
	
	private class StatusBar extends Image{
		private float status = 0.0f;
		
		public StatusBar(int currentStatus, int maxStatus, String region, String resource) {
			super(new TextureRegion(new Texture(FileMap.resourcesToDirectory.get(resource)+region)));
			this.setScaleX(status);
		}
		
		public void update(int currentStatus, int maxStatus){
			status = (float) ((float)currentStatus / (float)maxStatus);
			this.setScaleX(status);
		}
		
	}

	public StatusScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		initComponents();
	}
	
	public void initComponents(){
		stage.clear();
		
		maxHealth = new TextButton(MenuTextDAO.getMenuText().maxHealth, 
				getSkin());
		maxHealth =ScreenUtils.defineButton(maxHealth, 0, 0, 0,
				0, super.getSkin());
		maxHealth.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getProfile().getPlayer().setMaxHealth(game.getProfile().getPlayer().getMaxHealth()+50);
				updateBar();
			}
		});
		
		maxEnergyBar = new StatusBar(game.getProfile().getPlayer().getMaxEnergy(), 1000, "blue_bar.png", "skin");
		
		maxEnergy = new TextButton(MenuTextDAO.getMenuText().maxEnergy
				+ "(" + game.getProfile().getPlayer().getMaxEnergy() + "/" + String.valueOf(1000) + ")", 
				getSkin());
		maxEnergy =ScreenUtils.defineButton(maxEnergy, 0, 0, 0,
				0, super.getSkin());
		maxEnergy.addListener(new ClickListener(){
			 @Override
			public void clicked(InputEvent event, float x, float y) {
				game.getProfile().getPlayer().setMaxEnergy(game.getProfile().getPlayer().getMaxEnergy()+10);
				maxEnergyBar.update(game.getProfile().getPlayer().getMaxEnergy(), 1000);
				maxEnergy.setText(MenuTextDAO.getMenuText().maxEnergy
				+ "(" + game.getProfile().getPlayer().getMaxEnergy() + "/" + String.valueOf(1000) + ")");
			}
		});
		
		energyRecovery = new TextButton(MenuTextDAO.getMenuText().energyRecovery, 
				getSkin());
		energyRecovery =ScreenUtils.defineButton(energyRecovery, 0, 0, 0,
				0, super.getSkin());
		energyRecovery.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getProfile().getPlayer().setEnergyRecovery(game.getProfile().getPlayer().getEnergyRecovery()+1);
				updateBar();
			}
		});
		
		deckCapacity = new TextButton(MenuTextDAO.getMenuText().deckCapacity, 
				getSkin());
		deckCapacity =ScreenUtils.defineButton(deckCapacity, 0, 0, 0,
				0, super.getSkin());
		deckCapacity.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getProfile().getPlayer().setDeckCapacity(game.getProfile().getPlayer().getDeckCapacity()+5);
				updateBar();
			}
		});
		
		maxCardPoints = new TextButton(MenuTextDAO.getMenuText().maxCardPoints, 
				getSkin());
		maxCardPoints =ScreenUtils.defineButton(maxCardPoints, 0, 0, 0,
				0, super.getSkin());
		maxCardPoints.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getProfile().getPlayer().setMaxCardPoints(game.getProfile().getPlayer().getMaxCardPoints()+10);
				updateBar();
			}
		});
		
		doubleDraw = new TextButton(MenuTextDAO.getMenuText().doubleDraw, 
				getSkin());
		doubleDraw =ScreenUtils.defineButton(doubleDraw, 0, 0, 0,
				0, super.getSkin());
		doubleDraw.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getProfile().getPlayer().setDoubleDraw(game.getProfile().getPlayer().getDoubleDraw()+(float)0.1);
				updateBar();
			}
		});
		
		returnButton = new TextButton(MenuTextDAO.getMenuText().returnToStart,
				super.getSkin());
		returnButton = ScreenUtils.defineButton(returnButton, 0, 0, 0, 0,
				super.getSkin());
		returnButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_6");
				game.setScreenWithTransition(new RpgMenu(game, controller));
			}
		});
		
		maxHealthBar = new Image(this.getSkin().getDrawable("red_bar"));
//		maxEnergyBar = new Image(this.getSkin().getDrawable("blue_bar"));
		energyRecoveryBar = new Image(this.getSkin().getDrawable("blue2_bar"));
		deckCapacityBar = new Image(this.getSkin().getDrawable("green_bar"));
		maxCardPointsBar = new Image(this.getSkin().getDrawable("yellow_bar"));
		doubleDrawBar = new Image(this.getSkin().getDrawable("purple_bar"));
		
		//label com os valores
//		maxHealthLbl= new Label("", super.getSkin());
//		maxHealthLbl.setText(String.valueOf(game.getProfile().getPlayer().getMaxHealth())+"/1000");
//		maxHealthLbl.setPosition(maxHealthBar.getX(), maxHealthBar.getY());
//		maxHealthLbl.setStyle(new LabelStyle(getSkin().getFont("basic-font"),
//				Color.WHITE));
		
		Table statusTable = new Table();
		statusTable.defaults().width(COLUMN).height(ROW).padTop(10);
		if (game.getMode() == RunMode.DEBUG)
			statusTable.debug();
		statusTable.defaults();
		
		statusTable.add(maxHealth);
		statusTable.add(maxHealthBar).width(BAR_WIDTH);
		statusTable.row();
		statusTable.add(maxEnergy);
		statusTable.add(maxEnergyBar).width(BAR_WIDTH);
		statusTable.row();
		statusTable.add(energyRecovery);
		statusTable.add(energyRecoveryBar).width(BAR_WIDTH);
		statusTable.row();
		statusTable.add(deckCapacity);
		statusTable.add(deckCapacityBar).width(BAR_WIDTH);
		statusTable.row();
		statusTable.add(maxCardPoints);
		statusTable.add(maxCardPointsBar).width(BAR_WIDTH);
		statusTable.row();
		statusTable.add(doubleDraw);
		statusTable.add(doubleDrawBar).width(BAR_WIDTH);
		statusTable.row();
		statusTable.add(returnButton);
		statusTable.setPosition(500, 300);
		
		stage.addActor(statusTable);
	}
	
	public void updateBar() {
		maxHealthBar.setScaleX((float)game.getProfile().getPlayer().getMaxHealth()/1000);
//		maxEnergyBar.setScaleX((float)game.getProfile().getPlayer().getMaxEnergy()/1000);
		energyRecoveryBar.setScaleX((float)game.getProfile().getPlayer().getEnergyRecovery()/10);
		deckCapacityBar.setScaleX((float)game.getProfile().getPlayer().getDeckCapacity()/100);
		maxCardPointsBar.setScaleX((float)game.getProfile().getPlayer().getMaxCardPoints()/100);
		doubleDrawBar.setScaleX(game.getProfile().getPlayer().getDoubleDraw());
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
