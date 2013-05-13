package br.edu.ifsp.pds.shadowstruggles.scripts;

import br.edu.ifsp.pds.shadowstruggles.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.object2d.Effect2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * Mineralogy action. Don't have an active effect, just self-destroy after 2 min.
 * 
 */


public class MineralogyAction extends CardAction {

	private boolean finished;
	private boolean started;
	public MineralogyAction() {
		this.finished = false;
		this.started = false;
	}

	@Override
	public void doAction(BattlePlatform platform, Image img,float delta) {

		Effect2D f = (Effect2D) img;

		if (finished) {
			f.getController().removeCard(f.getEffect());
		}
		if (!started) {
			img.setX(img.getX() + 24);
			started = true;
		}
	}

	@Override
	public void update(Image f1) {
		Effect2D f = (Effect2D) f1;
		f.setStateTime(Gdx.graphics.getDeltaTime() + f.getStateTime());		
		f.setCurrentFrame(f.getAnimation().getKeyFrame(f.getStateTime(), true));
		
	
		if (f.getStateTime() > 120f) {
			finished = true;
		}
	}

	@Override
	public void write(Json json) {
		
	}
	

	@Override
	public void read(Json json, JsonValue jsonData) {
		//this.id = json.readValue("id", Integer.class, jsonData);
	}
	
	@Override
	public CardAction copy() {
		return new MineralogyAction();
	}
}
