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

public class StatusScreen extends BaseScreen{
	private static final int COLUMN = 350;
	private static final int ROW = 60;
	private static final int BAR_WIDTH = 550;
	private static final int MAX_HEALTH_LIMIT = 1000;
	private static final int MAX_ENERGY_LIMIT = 500;
	private static final int ENERGY_RECOVERY_LIMIT = 10;
	private static final int DECK_CAPACITY_LIMIT = 100;
	private static final int MAX_CARD_POINTS_LIMIT = 100;
	private static final float DOUBLE_DRAW_LIMIT = 0.7f; 
	
	private TextButton maxHealth;
	private TextButton maxEnergy;
	private TextButton energyRecovery;
	private TextButton deckCapacity;
	private TextButton maxCardPoints;
	private TextButton doubleDraw;
	private TextButton returnButton;
	
	private Image maxHealthBar;
	private Image maxEnergyBar;
	private Image energyRecoveryBar;
	private Image deckCapacityBar;
	private Image maxCardPointsBar;
	private Image doubleDrawBar;
	

	public StatusScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		initComponents();
	}
	
	public void initComponents(){
		stage.clear();
		
		maxHealth = new TextButton(MenuTextDAO.getMenuText().maxHealth 
				+ "(" + game.getProfile().getPlayer().getMaxHealth() + "/"
				+ String.valueOf(MAX_HEALTH_LIMIT)+")" , getSkin());
		maxHealth =ScreenUtils.defineButton(maxHealth, 0, 0, 0,
				0, super.getSkin());
		maxHealth.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(game.getProfile().getPlayer().getMaxHealth()<MAX_HEALTH_LIMIT){
					game.getProfile().getPlayer().setMaxHealth(game.getProfile().getPlayer().getMaxHealth()+100);
					update();
				}
			}
		});
				
		maxEnergy = new TextButton(MenuTextDAO.getMenuText().maxEnergy
				+ "(" + game.getProfile().getPlayer().getMaxEnergy() + "/"
				+ String.valueOf(MAX_ENERGY_LIMIT)+")" , getSkin());
		maxEnergy =ScreenUtils.defineButton(maxEnergy, 0, 0, 0,
				0, super.getSkin());
		maxEnergy.addListener(new ClickListener(){
			 @Override
			public void clicked(InputEvent event, float x, float y) {
				 if(game.getProfile().getPlayer().getMaxEnergy()<MAX_ENERGY_LIMIT){
					 game.getProfile().getPlayer().setMaxEnergy(game.getProfile().getPlayer().getMaxEnergy()+50);
						update();
				 }
				
			}
		});
		
		energyRecovery = new TextButton(MenuTextDAO.getMenuText().energyRecovery
				+ "(" + game.getProfile().getPlayer().getEnergyRecovery() + "/"
				+ String.valueOf(ENERGY_RECOVERY_LIMIT) +")", getSkin());
		energyRecovery =ScreenUtils.defineButton(energyRecovery, 0, 0, 0,
				0, super.getSkin());
		energyRecovery.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(game.getProfile().getPlayer().getEnergyRecovery()<ENERGY_RECOVERY_LIMIT){
					game.getProfile().getPlayer().setEnergyRecovery(game.getProfile().getPlayer().getEnergyRecovery()+1);
					update();
				}
				
			}
		});
		
		deckCapacity = new TextButton(MenuTextDAO.getMenuText().deckCapacity
				+ "(" + game.getProfile().getPlayer().getDeckCapacity() + "/"
				+ String.valueOf(DECK_CAPACITY_LIMIT)+")", getSkin());
		deckCapacity =ScreenUtils.defineButton(deckCapacity, 0, 0, 0,
				0, super.getSkin());
		deckCapacity.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(game.getProfile().getPlayer().getDeckCapacity()<DECK_CAPACITY_LIMIT){
					game.getProfile().getPlayer().setDeckCapacity(game.getProfile().getPlayer().getDeckCapacity()+10);
					update();
				}
			}
		});
		
		maxCardPoints = new TextButton(MenuTextDAO.getMenuText().maxCardPoints
				+ "(" + game.getProfile().getPlayer().getMaxCardPoints() + "/"
				+ String.valueOf(MAX_CARD_POINTS_LIMIT)+")", getSkin());
		maxCardPoints =ScreenUtils.defineButton(maxCardPoints, 0, 0, 0,
				0, super.getSkin());
		maxCardPoints.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(game.getProfile().getPlayer().getMaxCardPoints()<MAX_CARD_POINTS_LIMIT){
					game.getProfile().getPlayer().setMaxCardPoints(game.getProfile().getPlayer().getMaxCardPoints()+10);
					update();
				}
			}
		});
		
		doubleDraw = new TextButton(MenuTextDAO.getMenuText().doubleDraw
				+ "(" + game.getProfile().getPlayer().getDoubleDraw()*100 + "/"+DOUBLE_DRAW_LIMIT+")", 
				getSkin());
		doubleDraw =ScreenUtils.defineButton(doubleDraw, 0, 0, 0,
				0, super.getSkin());
		doubleDraw.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(game.getProfile().getPlayer().getDoubleDraw()<DOUBLE_DRAW_LIMIT){
					game.getProfile().getPlayer().setDoubleDraw(game.getProfile().getPlayer().getDoubleDraw()+0.07f);
					update();
				}
			}
		});
		
		returnButton = new TextButton("Save & "+MenuTextDAO.getMenuText().returnToStart,
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
		maxEnergyBar = new Image(this.getSkin().getDrawable("blue_bar"));
		energyRecoveryBar = new Image(this.getSkin().getDrawable("blue2_bar"));
		deckCapacityBar = new Image(this.getSkin().getDrawable("green_bar"));
		maxCardPointsBar = new Image(this.getSkin().getDrawable("yellow_bar"));
		doubleDrawBar = new Image(this.getSkin().getDrawable("purple_bar"));
		
		update();
		
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
	
	public void update() {
		
		maxHealth.setText(MenuTextDAO.getMenuText().maxEnergy
				+ "(" + game.getProfile().getPlayer().getMaxHealth() + "/"
						+ String.valueOf(MAX_HEALTH_LIMIT)+")");
		maxHealthBar.setScaleX((float) ((float)game.getProfile().getPlayer().getMaxHealth() / (float)MAX_HEALTH_LIMIT));
		
		maxEnergy.setText(MenuTextDAO.getMenuText().maxEnergy
				+ "(" + game.getProfile().getPlayer().getMaxEnergy() + "/"
						+ String.valueOf(MAX_ENERGY_LIMIT)+")");
		maxEnergyBar.setScaleX((float) ((float)game.getProfile().getPlayer().getMaxEnergy() / (float)MAX_ENERGY_LIMIT));
		
		energyRecovery.setText(MenuTextDAO.getMenuText().energyRecovery
				+ "(" + game.getProfile().getPlayer().getEnergyRecovery() + "/"
						+ String.valueOf(ENERGY_RECOVERY_LIMIT)+")");
		energyRecoveryBar.setScaleX((float) ((float)game.getProfile().getPlayer().getEnergyRecovery() / (float)ENERGY_RECOVERY_LIMIT));
		
		deckCapacity.setText(MenuTextDAO.getMenuText().deckCapacity
				+ "(" + game.getProfile().getPlayer().getDeckCapacity() + "/"
						+ String.valueOf(DECK_CAPACITY_LIMIT)+")");
		deckCapacityBar.setScaleX((float) ((float)game.getProfile().getPlayer().getDeckCapacity() / (float)DECK_CAPACITY_LIMIT));
		
		maxCardPoints.setText(MenuTextDAO.getMenuText().maxCardPoints
				+ "(" + game.getProfile().getPlayer().getMaxCardPoints()+ "/"
						+ String.valueOf(MAX_CARD_POINTS_LIMIT)+")");
		maxCardPointsBar.setScaleX((float) ((float)game.getProfile().getPlayer().getMaxCardPoints() / (float)MAX_CARD_POINTS_LIMIT));
		
		doubleDraw.setText(MenuTextDAO.getMenuText().doubleDraw
				+ "(" + game.getProfile().getPlayer().getDoubleDraw()*100 + "%/"
						+ String.valueOf(DOUBLE_DRAW_LIMIT*100)+"%)");
		doubleDrawBar.setScaleX(game.getProfile().getPlayer().getDoubleDraw()/DOUBLE_DRAW_LIMIT);
		
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
