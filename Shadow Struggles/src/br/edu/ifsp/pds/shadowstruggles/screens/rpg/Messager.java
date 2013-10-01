package br.edu.ifsp.pds.shadowstruggles.screens.rpg;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * Displays messages on an {@link RpgScreen} through a background Image and a
 * Label.
 */
public class Messager {
	private Image background;
	private Label textLbl;

	public Messager(String text, ShadowStruggles game) {
		Skin skin = ((BaseScreen) game.getScreen()).getSkin();

		textLbl = new Label(text, skin);
		textLbl = ScreenUtils.defineLabel(textLbl, 20, 230, 910, 180);
		textLbl.setStyle(new LabelStyle(skin.getFont("andalus-font"),
				Color.WHITE));
		textLbl.setWrap(true);

		background = new Image(skin, "default-window");
		background = ScreenUtils.defineImage(background, 0, 200, 960, 180);
	}

	public void showOnScreen(RpgScreen screen) {
		screen.getStage().addActor(background);
		screen.getStage().addActor(textLbl);
	}
}
