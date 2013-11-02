package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import br.edu.ifsp.pds.shadowstruggles.model.scenes.Decision.Choice;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

/**
 * Shows a menu for selecting choices at an {@link RpgScreen}.
 */
public class DecisionDisplayer {
	private Image box;
	private Table table;
	private ScrollPane scroll;
	private BaseScreen screen;

	private Array<Choice> choices;

	public DecisionDisplayer(BaseScreen screen, Array<Choice> choices) {
		this.screen = screen;
		this.choices = choices;
		Skin skin = screen.getSkin();

		box = new Image(skin, "default-window");
		box = ScreenUtils.defineImage(box, 0, 0, screen.getWidth(),
				screen.getHeight());

		table = new Table();
		table.defaults().width(750).height(130);
		table.debug();
		table.setPosition(480, 280);

		buildTable();

		scroll = new ScrollPane(table, skin);
		scroll.setBounds(100, 100, 750, 500);
		scroll.setScrollingDisabled(true, false);
		scroll.setFadeScrollBars(false);
	}

	public void show() {
		screen.getStage().addActor(box);
		screen.getStage().addActor(scroll);
	}

	public void hide() {
		screen.getStage().removeActor(box);
		screen.getStage().removeActor(scroll);
	}

	private void buildTable() {
		Skin skin = screen.getSkin();

		int i = 0;
		for (final Choice choice : choices) {
			TextButton button = new TextButton(choice.text, skin, "decision");
			button.getLabel().setAlignment(Align.left);
			button.getLabel().setWrap(true);

			float y = screen.getHeight() - 130 - i
					* (button.getLabel().getHeight() + 45);
			float height = button.getLabel().getHeight() + 25;
			button = ScreenUtils
					.defineButton(button, 100, y, 750, height, skin);

			button.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					hide();
					choice.consequence.action();
				}
			});

			table.add(button);

			if (i < choices.size - 1) {
				table.row();
				i++;
			}
		}
	}
}
