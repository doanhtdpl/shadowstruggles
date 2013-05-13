package br.edu.ifsp.pds.shadowstruggles.model;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * A Scene is an object which represents a narrative sequence within the story.
 * Each Scene has some text, written in a specified language, a background
 * music, and is displayed on the screen on top of a background image. Some of
 * them also have a set of choices, thus bifurcating the story. That is why it
 * is necessary to specify which is the next Scene.
 */
public class Scene implements Serializable {

	public static final Scene FIRST_SCENE = new Scene();

	private int id;
	private int nextId;
	private String name;

	private String script;
	private String language;
	private String backgroundImage;
	private String backgroundMusic;
	private String[] choices;

	public Scene() {
		this.id = 0;
		this.setNextId(1000);
		this.name = "The Shadow Struggles";
		this.script = "Go beyond the clouds and the stars above, travel beneath the earth and the magma below, transcending both "
				+ "time and space. Once there, you will find a place in which neither \u0027life\u0027 nor \u0027matter\u0027 have any "
				+ "special meaning. The only reality is that of an endless opposition between two factions.\n\n\u0027Light\u0027 and "
				+ "\u0027Darkness\u0027 or \u0027Good\u0027 and \u0027Evil\u0027 is how mortals perceive these ethereal entities, "
				+ "but that is only because of their own limited existences. Their so precious universe is nothing but a small stain, "
				+ "residues of the war. An uninvited shadow.\n\nBy the time Light and Darkness took notice of it, the shadow had "
				+ "already grown significantly. In wonder, they decided to entertain themselves and try to solve the conflict in a "
				+ "different way, through that funny new toy. Thus, the Shadow Struggles began.";
		this.setLanguage("en_us");
		this.backgroundImage = "data/images/scenes/light_darkness_2.png";
		this.backgroundMusic = "intro";
		this.choices = new String[]{};
	}

	public int getId() {
		return this.id;
	}

	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getScript() {
		return script;
	}

	public String getBackgroundImage() {
		return this.backgroundImage;
	}

	public String getBackgroundMusic() {
		return this.backgroundMusic;
	}

	public String[] getChoices() {
		return choices;
	}

	public void setChoices(String[] choices) {
		this.choices = choices;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void write(Json json) {
		json.writeValue("id", this.id);
		json.writeValue("nextId", this.nextId);
		json.writeValue("name", this.name);
		json.writeValue("script", this.script);
		json.writeValue("language", this.language);
		json.writeValue("backgroundImage", this.backgroundImage);
		json.writeValue("backgroundMusic", this.backgroundMusic);
		json.writeValue("choices", this.choices);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.id = json.readValue("id", Integer.class, jsonData);
		this.nextId = json.readValue("nextId", Integer.class, jsonData);
		this.name = json.readValue("name", String.class, jsonData);
		this.script = json.readValue("script", String.class, jsonData);
		this.language = json.readValue("language", String.class, jsonData);
		this.backgroundImage = json.readValue("backgroundImage", String.class,
				jsonData);
		this.backgroundMusic = json.readValue("backgroundMusic", String.class,
				jsonData);
		this.choices = json.readValue("choices", String[].class, jsonData);
	}

}
