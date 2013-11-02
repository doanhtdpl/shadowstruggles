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
import br.edu.ifsp.pds.shadowstruggles.screens.MainScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

public class StatusScreen extends BaseScreen{
	private TextButton energy;
	private TextButton energyRestore;
	private TextButton deckSize;
	private TextButton deckPoints;
	private TextButton lifePoints;
	private TextButton doubleDraw;
	private TextButton returnButton;
	private Image background;

	public StatusScreen(ShadowStruggles game, Controller controller) {
		super(game, controller);
		initComponents();
	}
	
	public void initComponents(){
		stage.clear();
		background = new Image(this.getSkin().getDrawable("msbackground"));
		background.setScaleX(960f / 512f);
		background.setScaleY(640f / 380f);
		
		energy = new TextButton(MenuTextDAO.getMenuText().energy, 
				getSkin());
		energy =ScreenUtils.defineButton(energy, 0, 0, 0,
				0, super.getSkin());
		
		energyRestore = new TextButton(MenuTextDAO.getMenuText().energyRestore, 
				getSkin());
		energyRestore =ScreenUtils.defineButton(energyRestore, 0, 0, 0,
				0, super.getSkin());
		
		deckSize = new TextButton(MenuTextDAO.getMenuText().deckSize, 
				getSkin());
		deckSize =ScreenUtils.defineButton(deckSize, 0, 0, 0,
				0, super.getSkin());
		
		deckPoints = new TextButton(MenuTextDAO.getMenuText().deckPoints, 
				getSkin());
		deckPoints =ScreenUtils.defineButton(deckPoints, 0, 0, 0,
				0, super.getSkin());
		
		lifePoints = new TextButton(MenuTextDAO.getMenuText().lifePoints, 
				getSkin());
		lifePoints =ScreenUtils.defineButton(lifePoints, 0, 0, 0,
				0, super.getSkin());
		
		doubleDraw = new TextButton(MenuTextDAO.getMenuText().doubleDraw, 
				getSkin());
		doubleDraw =ScreenUtils.defineButton(doubleDraw, 0, 0, 0,
				0, super.getSkin());
		
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
		
		Table statusTable = new Table();
		statusTable.defaults().width(310).height(60).padTop(10);
		if (game.getMode() == RunMode.DEBUG)
			statusTable.debug();
		statusTable.defaults();
		
		statusTable.add(energy);
		statusTable.add().width(550);
		statusTable.row();
		statusTable.add(energyRestore);
		statusTable.add().width(550);
		statusTable.row();
		statusTable.add(deckSize);
		statusTable.add().width(550);
		statusTable.row();
		statusTable.add(deckPoints);
		statusTable.add().width(550);
		statusTable.row();
		statusTable.add(lifePoints);
		statusTable.add().width(550);
		statusTable.row();
		statusTable.add(doubleDraw);
		statusTable.add().width(550);
		statusTable.row();
		statusTable.add(returnButton);
		statusTable.add().width(550);
		statusTable.row();
		statusTable.setPosition(500, 300);
		
		
		stage.addActor(background);
		stage.addActor(statusTable);
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
