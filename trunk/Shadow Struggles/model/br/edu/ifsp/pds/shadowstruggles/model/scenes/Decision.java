package br.edu.ifsp.pds.shadowstruggles.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.screens.BaseScreen;
import br.edu.ifsp.pds.shadowstruggles.screens.rpg.DecisionDisplayer;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class Decision extends SceneItem {

	public class Choice implements Serializable {
		public String text;
		public SceneItem consequence;

		public Choice() {
			this.text = "";
			this.consequence = null;
		}

		public Choice(String text, SceneItem consequence) {
			this.text = text;
			this.consequence = consequence;
		}

		@Override
		public void read(Json json, JsonValue jsonData) {
			this.text = json.readValue("text", String.class, jsonData);
			this.consequence = json.readValue("consequence", SceneItem.class,
					jsonData);
		}

		@Override
		public void write(Json json) {
			json.writeValue("text", this.text);
			json.writeValue("consequence", this.consequence);
		}
	}

	public Array<Choice> choices;

	public Decision() {
		this.choices = new Array<Decision.Choice>();
	}

	public Decision(Array<Choice> choices) {
		this.choices = choices;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		this.choices = json.readValue("choices", Array.class, jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("choices", this.choices);
	}

	@Override
	public void action() {
		DecisionDisplayer displayer = new DecisionDisplayer(
				(BaseScreen) ShadowStruggles.getInstance().getScreen(), choices);
		displayer.show();
	}
}
