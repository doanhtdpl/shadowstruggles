package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TableLayoutTest extends BaseScreen{

	public TableLayoutTest(ShadowStruggles game) {
		super(game);
		
	}
	
	private void initComponents() {
		Label nameLabel = new Label("Name:", getSkin());
		Label nameText = new Label("label1",getSkin());
		Label addressLabel = new Label("Address:", getSkin());
		Label addressText = new Label("label2",getSkin());
		nameText.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("label 1 clicked");
				super.clicked(event, x, y);
			}
		});
		Table table = new Table();
		
	
		table.add(nameLabel);		
		table.add(nameText).width(100);
		table.row().height(100);
		table.add(addressLabel);
		table.add(addressText).width(100);
		//table.center();
		table.setX(200);
		table.setY(300);
		stage.addActor(table);
	}
	
	@Override
	public void resize(int width, int height) {		
		super.resize(width, height);
		initComponents();
	}

}
