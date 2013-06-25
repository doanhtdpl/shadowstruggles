package br.edu.ifsp.pds.shadowstruggles.model;

import br.edu.ifsp.pds.shadowstruggles.scripts.CardAction;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/***
 * A representation of an effect card on the field.
 */

public class Effect extends Card {
	private float duration;
	private boolean isImmediateEffect;
	private boolean isActivated = false;
	private boolean onFighter;

	public Effect() {
	}

	public Effect(BattlePlatform platform, int lane, int tile, String name,
			String nameVisualization, CardAction action, Image img) {
		super(platform, lane, tile, name, nameVisualization, action, img);
	}

	public Effect(String name, String nameVisualization, int energyCost,
			String description, int buyCost, CardAction action, float duration,
			boolean isImmediateEffect, boolean onFighter) {
		super(name, nameVisualization, energyCost, description, buyCost, action);
		this.onFighter = onFighter;
		this.duration = duration;
		this.isImmediateEffect = isImmediateEffect;
		this.direction = 1;
	}

	public boolean isImmediateEffect() {
		return isImmediateEffect;
	}

	public void setImmediateEffect(boolean isImmediateEffect) {
		this.isImmediateEffect = isImmediateEffect;
	}

	public boolean isOnFighter() {
		return onFighter;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	@Override
	public void write(Json json) {
		super.write(json);
		json.writeValue("duration", this.duration);
		json.writeValue("isImmediateEffect", this.isImmediateEffect);
		json.writeValue("isActivated", this.isActivated);
		json.writeValue("onFighter", this.onFighter);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		this.duration = json.readValue("duration", Float.class, jsonData);
		this.isImmediateEffect = json.readValue("isImmediateEffect",
				Boolean.class, jsonData);
		this.isActivated = json.readValue("isActivated", Boolean.class,
				jsonData);
		this.onFighter = json.readValue("onFighter", Boolean.class, jsonData);
	}
}
