package br.edu.ifsp.pds.shadowstruggles.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.Loader.Asset;
import br.edu.ifsp.pds.shadowstruggles.model.scenes.NovelScene;
import br.edu.ifsp.pds.shadowstruggles.object2d.Arrow;
import br.edu.ifsp.pds.shadowstruggles.screens.utils.ScreenUtils;

public class NovelScreen extends BaseScreen {
	private NovelScene scene;
	private ShadowStruggles game;

	private Image background;
	private Image box;
	private Arrow arrow;
	private ScrollPane scroll;

	public NovelScreen(ShadowStruggles game, NovelScene scene) {
		super(game);

		this.scene = scene;
	}

	public void initComponents() {
		Skin skin = getSkin();

		background = new Image(game.getTexture(scene.getBackground(),
				"novel_images"));
		background = ScreenUtils.defineImage(background, 0, 0, width, height);
		box = new Image(skin, "box");
		box = ScreenUtils.defineImage(box, 30, 30, width - 70, height - 50);

		arrow = new Arrow(1, skin);
		arrow.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAudio().playSound("button_7");
				scene.getParentScene().runNextItem();
			}
		});
		arrow.setScaleY(6.0f);
		arrow.translate(5, -10);

		Label text = new Label(scene.getText(), skin);
		text.setStyle(new LabelStyle(super.getSkin().getFont("andalus-font"),
				Color.WHITE));
		text.setWrap(true);
		
		Table table = new Table();
		table.defaults().width(750).height(text.getText().length());
		table.debug();
		table.setPosition(480, 280);
		table.add(text);

		scroll = new ScrollPane(table, skin);
		scroll.setBounds(100, 100, 750, 500);
		scroll.setScrollingDisabled(true, false);
		scroll.setFadeScrollBars(false);

		stage.addActor(background);
		stage.addActor(box);
		stage.addActor(arrow);
		stage.addActor(scroll);
	}

	@Override
	public Array<Asset> texturesToLoad() {
		Array<Asset> textures = new Array<Asset>();
		textures.add(new Asset(scene.getBackground() + ".jpeg", "novel_images"));
		return textures;
	}
}
